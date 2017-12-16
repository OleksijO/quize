package com.pdp.quize.message.two.way;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import java.io.Serializable;

public class ObjectMessageTwoWayClientFactory<Req extends Serializable, Res extends Serializable> {
    private final static Logger LOGGER = LoggerFactory.getLogger(ObjectMessageTwoWayClientFactory.class);

    private Session session;
    private TwoWayMessageClient<Req, Res> client;

    private ObjectMessageTwoWayClientFactory(Session session, TwoWayMessageClient<Req, Res> client) {
        this.session = session;
        this.client = client;
    }

    public TwoWayMessageClient<Req, Res> getClient() {
        return client;
    }

    public void destroy() {
        try {
            session.close();
        } catch (JMSException e) {
            LOGGER.error("Error occurred while closing session", e);
        }
    }

    public static class Builder<Req extends Serializable, Res extends Serializable> {
        private Connection connection;
        private String queueName;

        public Builder<Req, Res> withConnection(Connection connection) {
            this.connection = connection;
            return this;
        }

        public Builder<Req, Res> withQueueName(String queueName) {
            this.queueName = queueName;
            return this;
        }

        public ObjectMessageTwoWayClientFactory<Req, Res> build() {
            try {
                checkParams();

                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Queue commandQueue = session.createQueue("command_" + queueName);
                Queue responseQueue = session.createQueue("answer_" + queueName);
                TwoWayMessageClient<Req, Res> client =
                        new TwoWayMessageClient<>(session, commandQueue, responseQueue);
                ObjectMessageTwoWayClientFactory<Req, Res> messageManager =
                        new ObjectMessageTwoWayClientFactory<>(session, client);
                connection.start();
                return messageManager;

            } catch (JMSException e) {
                LOGGER.error("Error occurred while instantiating TextMessageManager", e);
                throw new RuntimeException(e);
            }
        }

        private void checkParams() {
            if (connection == null) {
                throw new IllegalArgumentException("Connection factory should be specified");
            }
            if (queueName == null) {
                throw new IllegalArgumentException("Queue name should be specified");
            }
        }
    }
}
