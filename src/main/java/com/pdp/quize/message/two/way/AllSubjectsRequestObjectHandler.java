package com.pdp.quize.message.two.way;

import com.pdp.quize.domain.dto.SubjectDto;
import com.pdp.quize.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllSubjectsRequestObjectHandler implements RequestObjectHandler<String, ArrayList<SubjectDto>> {
    @Autowired
    private SubjectService subjectService;

    @Override
    public ArrayList<SubjectDto> handle(String reqObject) {
        if ("Get All".equalsIgnoreCase(reqObject)){
            List<SubjectDto> subjects = subjectService.getAll();
            return new ArrayList<>(subjects);
        }
        return new ArrayList<>();
    }
}
