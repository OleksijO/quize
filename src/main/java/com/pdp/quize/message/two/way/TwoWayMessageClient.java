package com.pdp.quize.message.two.way;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.io.Serializable;

public class TwoWayMessageClient<Req extends Serializable, Res extends Serializable> {
    private Session session;
    private Queue reqQueue;
    private Queue resQueue;
    private ObjectPublisher<Req> publisher;
    private MessageConsumer messageConsumer;
    private Res result;

    private final Object monitor = new Object();

    @Autowired
    public TwoWayMessageClient(Session session, Queue reqQueue, Queue resQueue) {
        this.session = session;
        this.reqQueue = reqQueue;
        this.resQueue = resQueue;
        init();
    }

    @PostConstruct
    public void init() {
        this.publisher = new ObjectPublisher<>(session, reqQueue);

        try {

            this.messageConsumer = session.createConsumer(resQueue);
            this.messageConsumer.setMessageListener(createListener());

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public Res send(Req req) {

        result = null;
        synchronized (monitor){
            publisher.send(req);
            waitForResponse();
        }
        return result;

    }

    private void waitForResponse() {
        try {
            monitor.wait(1000);
        } catch (InterruptedException e) {
           throw new RuntimeException(e);
        }
    }

    public MessageListener createListener() {
        return message -> {
            try {
                synchronized (monitor) {
                    result = (Res) ((ObjectMessage) message).getObject();
                    monitor.notifyAll();
                }
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }

        };
    }
}
