package com.pdp.quize.message.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.LinkedList;
import java.util.List;

public class TextMessageManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(TextMessageManager.class);

    private Session session;
    private Queue queue;

    public TextMessageManager(Session session, Queue queue) {
        this.session = session;
        this.queue = queue;
    }

    public TextMessagePublisher createPublisher() {
        return new TextMessagePublisher(session, queue);
    }

    public void destroy() {
        try {
            session.close();
        } catch (JMSException e) {
            LOGGER.error("Error occurred while closing session", e);
        }
    }

    public static class Builder {
        private Connection connection;
        private String queueName;
        private List<MessageListener> subscribers = new LinkedList<>();

        public Builder withConnection(Connection connection) {
            this.connection = connection;
            return this;
        }

        public Builder withQueueName(String queueName) {
            this.queueName = queueName;
            return this;
        }

        public Builder withSubscriber(MessageListener subscriber) {
            this.subscribers.add(subscriber);
            return this;
        }

        public TextMessageManager build() {
            try {
                checkParams();

                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Queue queue = session.createQueue(queueName);
                TextMessageManager messageManager =
                        new TextMessageManager(session, queue);
                registerSubscribers(session, queue);
                connection.start();
                return messageManager;

            } catch (JMSException e) {
                LOGGER.error("Error occurred while instantiating TextMessageManager", e);
                throw new RuntimeException(e);
            }
        }

        private void registerSubscribers(Session session, Queue queue) {
            subscribers.forEach(subscriber -> registerSubscriber(subscriber, session, queue));
        }

        private void registerSubscriber(MessageListener subscriber, Session session, Queue queue) {
            try {
                MessageConsumer consumer = session.createConsumer(queue);
                consumer.setMessageListener(subscriber);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        private void checkParams() {
            if (connection == null) {
                throw new IllegalArgumentException("Connection factory should be specified");
            }
            if (queueName == null) {
                throw new IllegalArgumentException("Queue name should be specified");
            }
            if (subscribers.isEmpty()) {
                throw new IllegalArgumentException("There should be at least one subscriber being specified");
            }
        }
    }
}
