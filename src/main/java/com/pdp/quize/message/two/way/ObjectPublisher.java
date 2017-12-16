package com.pdp.quize.message.two.way;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.Serializable;

public class ObjectPublisher<T extends Serializable> {
    private final static Logger LOGGER = LoggerFactory.getLogger(ObjectPublisher.class);

    private MessageProducer messageProducer;
    private Session session;

    public ObjectPublisher(Session session, Queue queue) {
        this.session = session;
        try {
            this.messageProducer = session.createProducer(queue);
        } catch (JMSException e) {
            LOGGER.error("Error occurred while instantiating Publisher", e);
        }
    }

    public void send(T object) {
        try {
            Message message = session.createObjectMessage(object);
            messageProducer.send(message);
        } catch (JMSException e) {
            LOGGER.error(String.format("Error occurred while sending object: %s", object), e);
        }

    }
}
