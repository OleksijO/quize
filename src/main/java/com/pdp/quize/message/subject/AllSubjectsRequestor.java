package com.pdp.quize.message.subject;

import com.pdp.quize.domain.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.List;
import java.util.UUID;

@Component
public class AllSubjectsRequestor {

    private final JmsTemplate jmsTemplate;
    private final MessageConverter messageConverter;

    @Autowired
    public AllSubjectsRequestor(final JmsTemplate jmsTemplate, MessageConverter messageConverter) {
        this.jmsTemplate = jmsTemplate;
        this.messageConverter = messageConverter;
    }

    public List<SubjectDto> request(String queue) {
        // Must pass true as the second param to start the connection
        try {
            Message message = jmsTemplate.execute(
                    new ProducerConsumer(new SubjectActionMessage(ActionMessage.GET_ALL),
                            queue,
                            jmsTemplate.getDestinationResolver()),
                    true);
            return ((SubjectActionMessage)
                    messageConverter
                            .fromMessage(message))
                    .getSubjects();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private final class ProducerConsumer implements SessionCallback<Message> {

        private static final int TIMEOUT = 5000;

        private final SubjectActionMessage msg;

        private final DestinationResolver destinationResolver;

        private final String queue;

        public ProducerConsumer(final SubjectActionMessage msg, String queue, final DestinationResolver destinationResolver) {
            this.msg = msg;
            this.queue = queue;
            this.destinationResolver = destinationResolver;
        }

        public Message doInJms(final Session session) {
            MessageConsumer consumer = null;
            MessageProducer producer = null;
            try {
                final String correlationId = UUID.randomUUID().toString();
                final Destination requestQueue =
                        destinationResolver.resolveDestinationName(session, queue + ".request", false);
                final Destination replyQueue =
                        destinationResolver.resolveDestinationName(session, queue + ".response", false);
                // Create the consumer first!
                consumer = session.createConsumer(replyQueue, "JMSCorrelationID = '" + correlationId + "'");
                Message message = messageConverter.toMessage(msg, session);
                message.setJMSCorrelationID(correlationId);
                message.setJMSReplyTo(replyQueue);
                // Send the request second!
                producer = session.createProducer(requestQueue);
                producer.send(requestQueue, message);
                // Block on receiving the response with a timeout
                return consumer.receive(TIMEOUT);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            } finally {
                // Don't forget to close your resources
                JmsUtils.closeMessageConsumer(consumer);
                JmsUtils.closeMessageProducer(producer);
            }
        }
    }
}
