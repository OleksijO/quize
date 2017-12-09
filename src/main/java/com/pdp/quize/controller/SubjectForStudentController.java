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

@RestController
@RequestMapping("/api/student")
public class SubjectForStudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectForStudentController.class);

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/subject", method = RequestMethod.DELETE)
    public SubjectDto remove(@RequestBody SubjectDto dto) {
        LOGGER.info("EXECUTING: remove()");

        subjectService.remove(dto);
        dto.setSubjectId(null);
        return dto;
    }

}
