package com.pdp.quize.message.two.way;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;

public class TwoWayMessageService<Req extends Serializable, Res extends Serializable>
        implements MessageListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(TwoWayMessageClient.class);

    private ObjectPublisher<Res> publisher;
    private RequestObjectHandler<Req, Res> requestObjectHandler;

    protected TwoWayMessageService(ObjectPublisher<Res> publisher,
                                   RequestObjectHandler<Req, Res> requestObjectHandler) {
        this.publisher = publisher;
        this.requestObjectHandler = requestObjectHandler;
    }

    @Override
    public void onMessage(Message message) {
        try {

            Req req = (Req)((ObjectMessage) message).getObject();
            publisher.send(handleRequestObject(req));

        } catch (JMSException e) {
            LOGGER.error("Error occurred.", e);
        }
    }

    protected Res handleRequestObject(Req reqObject){
        return requestObjectHandler.handle(reqObject);
    }
}
