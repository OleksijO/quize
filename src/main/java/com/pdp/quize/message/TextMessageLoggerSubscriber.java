package com.pdp.quize.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TextMessageLoggerSubscriber implements MessageListener {
    private final static Logger LOGGER = LoggerFactory.getLogger("MESSAGING");
    private final String name;

    public TextMessageLoggerSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onMessage(Message message) {
        try {
            String text = ((TextMessage) message).getText();
            LOGGER.info("" +
                    "\n--------------------------------------------------------------------------------------------\n" +
                    "TextMessageLoggerSubscriber " + name + " received the message: \n" + text +
                    "\n--------------------------------------------------------------------------------------------\n");
        } catch (JMSException e) {
            LOGGER.error("Error occured while retrieving text message", e);
        }
    }
}
