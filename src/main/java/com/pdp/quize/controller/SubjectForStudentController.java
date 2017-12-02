package com.pdp.quize.controller;

import com.pdp.quize.domain.dto.SubjectDto;
import com.pdp.quize.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class SubjectForStudentController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value="/subject", method = RequestMethod.DELETE)
    public SubjectDto remove(@RequestBody SubjectDto dto){
        subjectService.remove(dto);
        dto.setSubjectId(null);
        return dto;
    }

}
