package com.pdp.quize.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.*;

@Service
public class TextMessagePublisher {
    private final static Logger LOGGER = LoggerFactory.getLogger(TextMessagePublisher.class);

    private MessageProducer messageProducer;
    private Session session;
    private Queue queue;

    @Autowired
    public TextMessagePublisher(Session session, Queue queue) {
        this.session = session;
        this.queue = queue;
    }

    @PostConstruct
    public void init() {
        try {
            this.messageProducer = session.createProducer(queue);
        } catch (JMSException e) {
            LOGGER.error("Error occurred while instantiating Publisher", e);
        }
    }

    public void send(String textMessage) {
        try {
            Message message = session.createTextMessage(textMessage);
            messageProducer.send(message);
        } catch (JMSException e) {
            LOGGER.error(String.format("Error occurred while sending a message: %s", textMessage), e);
        }

    }
}
