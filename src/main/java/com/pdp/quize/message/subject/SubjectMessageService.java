package com.pdp.quize.message.subject;

import com.pdp.quize.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.HashMap;
import java.util.Map;

@Service
public class SubjectMessageService {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private MessageConverter messageConverter;

    private Map<ActionMessage, MessageHandler> handlers = new HashMap<ActionMessage, MessageHandler>(){{
        put(ActionMessage.DELETE, mes -> subjectService.remove(mes.getSubject()));
        put(ActionMessage.SAVE, mes -> subjectService.saveOrUpdate(mes.getSubjects()));
    }};


    @JmsListener(destination = "subjectControl", containerFactory = "myFactory")
    public void handleMessage(SubjectActionMessage actionMessage){

        handlers.get(actionMessage.getAction()).handle(actionMessage);

    }

    @JmsListener(destination = "subjectControl.request", containerFactory = "myFactory")
    public void handleSpecialMessage(Message message){
        try {
            String correlationId = message.getJMSCorrelationID();
            SubjectActionMessage actionMessage = (SubjectActionMessage) messageConverter.fromMessage(message);
            if (actionMessage.getAction() != ActionMessage.GET_ALL){
                actionMessage.setAction(ActionMessage.EMPTY);
            } else {
                actionMessage.setSubjects(subjectService.getAll());
            }
            jmsTemplate.send("subjectControl.response", createMessage(correlationId, actionMessage));
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private MessageCreator createMessage(String correlationId, SubjectActionMessage actionMessage) {
        return session -> {
            Message message = messageConverter.toMessage(actionMessage, session);
            message.setJMSCorrelationID(correlationId);
            return message;
        };
    }


    private interface MessageHandler{
        void handle(SubjectActionMessage subjectActionMessage);
    }
}
