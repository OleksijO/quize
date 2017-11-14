package com.pdp.quize.service;

import com.pdp.quize.constant.Role;
import com.pdp.quize.domain.Subject;
import com.pdp.quize.domain.User;
import com.pdp.quize.domain.dto.LoginDto;
import com.pdp.quize.domain.dto.RegistrationDto;
import com.pdp.quize.domain.dto.SubjectDto;
import com.pdp.quize.repository.SubjectRepository;
import com.pdp.quize.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Transactional
    public void saveOrUpdate(List<SubjectDto> subjectsDto){
        List<Subject> subjects = subjectsDto.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        subjectRepository.save(subjects);
    }

    public Subject toEntity(SubjectDto dto){
        return new Subject(dto.getSubjectId(), dto.getName());
    }

    public SubjectDto toDto(Subject entity){
        return new SubjectDto(entity.getSubjectId(), entity.getName());
    }

    public List<SubjectDto> getAll(){
        return StreamSupport
                .stream(subjectRepository.findAll().spliterator(), false)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void remove(SubjectDto dto) {
        if (dto!= null && dto.getSubjectId()!=null) {
            subjectRepository.delete(new BigInteger(dto.getSubjectId().toString()));
        }
    }
}

