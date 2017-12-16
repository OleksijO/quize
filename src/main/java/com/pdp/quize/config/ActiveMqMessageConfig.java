package com.pdp.quize.config;

import com.pdp.quize.domain.dto.SubjectDto;
import com.pdp.quize.message.text.TextMessageLoggerSubscriber;
import com.pdp.quize.message.text.TextMessageManager;
import com.pdp.quize.message.text.TextMessagePublisher;
import com.pdp.quize.message.two.way.AllSubjectsRequestObjectHandler;
import com.pdp.quize.message.two.way.ObjectMessageTwoWayClientFactory;
import com.pdp.quize.message.two.way.ObjectMessageTwoWayServiceFactory;
import com.pdp.quize.message.two.way.TwoWayMessageClient;
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
import java.util.ArrayList;

@Configuration
@ComponentScan({
        "com.pdp.quize.message",
})
public class ActiveMqMessageConfig {

    @Autowired
    private Environment env;
    @Autowired
    private AllSubjectsRequestObjectHandler allSubjectsRequestObjectHandler;

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
        String host = env.getProperty("message.broker.host");
        String port = env.getProperty("message.broker.port");

        return new ActiveMQConnectionFactory("tcp://" + host + ":" + port);
    }

    @Bean
    @DependsOn("brokerService")
    public ConnectionFactory connectionFactory1() {
        return createNewConnectionFactory();
    }

    private ConnectionFactory createNewConnectionFactory(){
        String host = env.getProperty("message.broker.host");
        String port = env.getProperty("message.broker.port");
        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://" + host + ":" + port);
        connectionFactory.setTrustAllPackages(true);

        return connectionFactory;
    }

    @Bean
    @DependsOn("brokerService")
    public ConnectionFactory connectionFactory2() {
        return createNewConnectionFactory();
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
    public TextMessageManager subjectQueueMessageManager() throws JMSException {
        return new TextMessageManager.Builder()
                .withConnection(connection())
                .withQueueName(env.getProperty("message.subject.queue.name"))
                .withSubscriber(new TextMessageLoggerSubscriber("TEXT MESSAGE LOGGER"))
                .build();
    }

    @Bean
    public TextMessagePublisher subjectPublisher() throws JMSException {
        return subjectQueueMessageManager().createPublisher();
    }

    @Bean
    public TwoWayMessageClient<String, ArrayList<SubjectDto>> allSubjectsViaMqGetter(){
        try {
            Connection connection = connectionFactory1().createConnection();
            ObjectMessageTwoWayClientFactory<String, ArrayList<SubjectDto>> factory =
                    new ObjectMessageTwoWayClientFactory.Builder<String, ArrayList<SubjectDto>>()
                    .withConnection(connection)
                    .withQueueName(env.getProperty("message.subject.queue.name"))
                    .build();
            return factory.getClient();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Object registerAllSubjectsViaMqService(){
        try {
            Connection connection = connectionFactory2().createConnection();
            return new ObjectMessageTwoWayServiceFactory.Builder<String, ArrayList<SubjectDto>>()
                    .withConnection(connection)
                    .withQueueName(env.getProperty("message.subject.queue.name"))
                    .withRequestObjectHandler(allSubjectsRequestObjectHandler)
                    .build();

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
