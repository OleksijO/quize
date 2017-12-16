package com.pdp.quize.message.subject;

import com.pdp.quize.domain.dto.SubjectDto;

import java.util.List;

public class SubjectActionMessage {
    private ActionMessage action;
    private List<SubjectDto> subjects;
    private SubjectDto subject;

    public SubjectActionMessage() {
        // default
    }

    public SubjectActionMessage(ActionMessage action, List<SubjectDto> subjects) {
        this.action = action;
        this.subjects = subjects;
    }

    public SubjectActionMessage(ActionMessage action, SubjectDto subject) {
        this.action = action;
        this.subject = subject;
    }

    public SubjectActionMessage(ActionMessage action) {
        this.action = action;
    }

    public ActionMessage getAction() {
        return action;
    }

    public void setAction(ActionMessage action) {
        this.action = action;
    }

    public List<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDto> subjects) {
        this.subjects = subjects;
    }

    public SubjectDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectDto subject) {
        this.subject = subject;
    }
}
