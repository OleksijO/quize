package com.pdp.quize.controller;

import com.pdp.quize.domain.dto.SubjectDto;
import com.pdp.quize.message.two.way.TwoWayMessageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/subject/all", method = RequestMethod.GET)
    public List<SubjectDto> getAll() {
        LOGGER.info("EXECUTING: DEANERY: Get All Subjects");

        return mqClient.send("Get All");
    }

}
