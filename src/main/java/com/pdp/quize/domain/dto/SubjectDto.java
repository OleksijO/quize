package com.pdp.quize.domain.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class SubjectDto implements Serializable {
    private Long subjectId;
    private String name;

    public SubjectDto() {
        // default
    }

    public SubjectDto(BigInteger subjectId, String name) {
        this.name= name;
        this.subjectId = subjectId.longValue();
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
