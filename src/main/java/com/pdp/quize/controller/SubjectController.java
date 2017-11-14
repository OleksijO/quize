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
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<SubjectDto> getAll(){
        return subjectService.getAll();
    }

    @RequestMapping(value="/all", method = RequestMethod.POST)
    public List<SubjectDto> update(@RequestBody List<SubjectDto> dtos){
        subjectService.saveOrUpdate(dtos);
        return subjectService.getAll();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public SubjectDto remove(@RequestBody SubjectDto dto){
        subjectService.remove(dto);
        dto.setSubjectId(null);
        return dto;
    }

}
