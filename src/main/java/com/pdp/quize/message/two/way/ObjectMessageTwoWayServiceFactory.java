package com.pdp.quize.message.two.way;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.Serializable;

public class ObjectMessageTwoWayServiceFactory<Req extends Serializable, Res extends Serializable> {
    private final static Logger LOGGER = LoggerFactory.getLogger(ObjectMessageTwoWayServiceFactory.class);

    private Session session;

    private ObjectMessageTwoWayServiceFactory(Session session) {
        this.session = session;
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
        private RequestObjectHandler<Req, Res> requestObjectHandler;

        public Builder<Req, Res> withConnection(Connection connection) {
            this.connection = connection;
            return this;
        }

        public Builder<Req, Res> withQueueName(String queueName) {
            this.queueName = queueName;
            return this;
        }

        public Builder<Req, Res> withRequestObjectHandler(RequestObjectHandler<Req, Res> handler) {
            this.requestObjectHandler = handler;
            return this;
        }

        public ObjectMessageTwoWayServiceFactory<Req, Res> build() {
            try {
                checkParams();

                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Queue commandQueue = session.createQueue("command_" + queueName);
                Queue responseQueue = session.createQueue("answer_" + queueName);
                ObjectPublisher<Res> responsePublisher = new ObjectPublisher<>(session, responseQueue);
                TwoWayMessageService<Req, Res> service =
                        new TwoWayMessageService<>(responsePublisher, requestObjectHandler);
                ObjectMessageTwoWayServiceFactory<Req, Res> messageManager =
                        new ObjectMessageTwoWayServiceFactory<>(session);
                registerService(service, session, commandQueue);
                connection.start();
                return messageManager;

            } catch (JMSException e) {
                LOGGER.error("Error occurred while instantiating TextMessageManager", e);
                throw new RuntimeException(e);
            }
        }

        private void registerService(MessageListener service, Session session, Queue commandQueue)
                throws JMSException {
            MessageConsumer consumer = session.createConsumer(commandQueue);
            consumer.setMessageListener(service);
        }

        private void checkParams() {
            if (connection == null) {
                throw new IllegalArgumentException("Connection factory should be specified");
            }
            if (queueName == null) {
                throw new IllegalArgumentException("Queue name should be specified");
            }
            if (requestObjectHandler == null) {
                throw new IllegalArgumentException("There should be request object handler");
            }
        }
    }
}
