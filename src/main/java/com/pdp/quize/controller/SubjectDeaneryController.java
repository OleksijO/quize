package com.pdp.quize.controller;

import com.pdp.quize.domain.dto.SubjectDto;
import com.pdp.quize.message.subject.ActionMessage;
import com.pdp.quize.message.subject.AllSubjectsRequestor;
import com.pdp.quize.message.subject.SubjectActionMessage;
import com.pdp.quize.message.two.way.TwoWayMessageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/deanery")
public class SubjectDeaneryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectDeaneryController.class);

    @Autowired
    private TwoWayMessageClient<String, ArrayList<SubjectDto>> mqClient;

    @Autowired
    private AllSubjectsRequestor allSubjectsRequestor;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    Environment environment;

    // GET: localhost:7080/deanery/mq/subject/all/

    @RequestMapping(value = "/mq/subject/all/", method = RequestMethod.GET)
    public List<SubjectDto> getAllByMq() {
        LOGGER.info("EXECUTING: DEANERY: Get All Subjects");

        return mqClient.send("Get All");
    }

    // GET: localhost:7080/deanery/jms/subject/all/

    @RequestMapping(value = "/jms/subject/all", method = RequestMethod.GET)
    public List<SubjectDto> getAll() {
        LOGGER.info("EXECUTING: DEANERY: Get All Subjects");

        String queueName = environment.getProperty("jms.subject.queue.name");
        return allSubjectsRequestor.request(queueName);
    }

    // DELETE localhost:7080/deanery/jms/subject/
    // body:{"subjectId": 38, "name": "12342134", "index": 1}

    @RequestMapping(value = "/jms/subject", method = RequestMethod.DELETE)
    public void remove(@RequestBody SubjectDto dto) {
        LOGGER.info("EXECUTING: DEANERY: remove()");

        String queueName = environment.getProperty("jms.subject.queue.name");
        SubjectActionMessage action = new SubjectActionMessage(ActionMessage.DELETE, dto);
        jmsTemplate.convertAndSend(queueName, action);
    }

    //POST localhost:7080/deanery/jms/subject/all/
    /* body:
        [{"subjectId": 1, "name": "1st subject", "index": 0},
        {"subjectId": 2, "name": "2nd subject", "index": 1},
        {"subjectId": 3, "name": "3rd subject", "index": 2},
        {"subjectId": 4, "name": "4th subject", "index": 3}]
     */

    @RequestMapping(value = "/jms/subject/all", method = RequestMethod.POST)
    public void update(@RequestBody List<SubjectDto> dtos) {
        LOGGER.info("EXECUTING: DEANERY: update()");

        String queueName = environment.getProperty("jms.subject.queue.name");
        SubjectActionMessage action = new SubjectActionMessage(ActionMessage.SAVE, dtos);

        jmsTemplate.convertAndSend(queueName, action);
    }



}
