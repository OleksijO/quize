package com.pdp.quize.controller;

import com.pdp.quize.domain.dto.SubjectDto;
import com.pdp.quize.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tutor")
public class SubjectForTutorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectForTutorController.class);

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/subject/all", method = RequestMethod.POST)
    public List<SubjectDto> update(@RequestBody List<SubjectDto> dtos) {
        LOGGER.info("EXECUTING: update()");

        if (System.currentTimeMillis() % 4 == 0) {
            LOGGER.info("EXECUTING: update(): Ooops....");
            throw new RuntimeException("Ooops....");
        }

        subjectService.saveOrUpdate(dtos);
        return subjectService.getAll();
    }

}
