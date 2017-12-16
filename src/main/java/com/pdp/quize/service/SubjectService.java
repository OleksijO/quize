package com.pdp.quize.service;

import com.pdp.quize.domain.Subject;
import com.pdp.quize.domain.dto.SubjectDto;
import com.pdp.quize.message.text.TextMessagePublisher;
import com.pdp.quize.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired @Qualifier("subjectPublisher")
    private TextMessagePublisher publisher;

    @Transactional
    public void saveOrUpdate(List<SubjectDto> subjectsDto){
        List<Subject> subjects = subjectsDto.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        subjectRepository.save(subjects);
        sendMessage("Subject list updated. New list:\n" + buildSubjectList(subjects));
    }

    private void sendMessage(String text) {
        publisher.send(text);
    }

    private String buildSubjectList(List<Subject> subjects) {
        return subjects.stream()
                .map(Subject::getName)
                .collect(Collectors.joining("\n"));
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
            sendMessage("Subject list updated. Subject [" + dto.getName() + "] is no longer available");
        }
    }
}

