package com.pdp.quize.controller;

import com.pdp.quize.domain.dto.SubjectDto;
import com.pdp.quize.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value="/subject/all", method = RequestMethod.GET)
    public List<SubjectDto> getAll(){
        System.out.println("SubjectController#getAll");
        return subjectService.getAll();
    }

}
