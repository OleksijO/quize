package com.pdp.quize.config;

import com.pdp.quize.message.MessageManager;
import com.pdp.quize.message.TextMessageLoggerSubscriber;
import com.pdp.quize.message.TextMessagePublisher;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import javax.jms.*;
import java.net.URI;

@Configuration
@ComponentScan({
        "com.pdp.quize.message",
})
public class ActiveMqMessageConfig {

    @Autowired
    private Environment env;

    @Bean(destroyMethod = "stop")
    public BrokerService brokerService() {

        String host = env.getProperty("message.broker.host");
        String port = env.getProperty("message.broker.port");

        try {

            BrokerService broker = BrokerFactory.createBroker(
                    new URI("broker:(tcp://" + host + ":" + port + ")"));
            broker.setPersistent(false);
            broker.start();

            return broker;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    @DependsOn("brokerService")
    public ConnectionFactory connectionFactory() {
        brokerService();
        String host = env.getProperty("message.broker.host");
        String port = env.getProperty("message.broker.port");

        return new ActiveMQConnectionFactory("tcp://" + host + ":" + port);
    }

    @Bean(destroyMethod = "close")
    public Connection connection() throws JMSException {
        return connectionFactory()
                .createConnection();
    }

    @Bean(destroyMethod = "close")
    public Session session() throws JMSException {
        return connection()
                .createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    @Bean
    public Queue subjectQueue() throws JMSException {
        return session()
                .createQueue(env.getProperty("message.subject.queue.name"));
    }

    @Bean(destroyMethod = "destroy")
    public MessageManager subjectQueueMessageManager() throws JMSException {
        return new MessageManager.Builder()
                .withConnection(connection())
                .withQueueName(env.getProperty("message.subject.queue.name"))
                .withSubscriber(new TextMessageLoggerSubscriber("TEXT MESSAGE LOGGER"))
                .build();
    }

    @Bean
    public TextMessagePublisher subjectPublisher() throws JMSException {
        return subjectQueueMessageManager().createPublisher();
    }
}
