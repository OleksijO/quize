package com.pdp.quize.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(19) unsigned", unique = true, nullable = false, updatable = false,
            insertable = false)
    private BigInteger subjectId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User tutor;
    @ManyToMany(mappedBy = "subjects")
    private List<User> students;
    @OneToMany(mappedBy = "subject")
    private List<Test> tests;

    public BigInteger getSubjectId() {
        return subjectId;
    }

    public Subject setSubjectId(BigInteger subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }

    public User getTutor() {
        return tutor;
    }

    public Subject setTutor(User tutor) {
        this.tutor = tutor;
        return this;
    }

    public List<User> getStudents() {
        return students;
    }

    public Subject setStudents(List<User> students) {
        this.students = students;
        return this;
    }

    public List<Test> getTests() {
        return tests;
    }

    public Subject setTests(List<Test> tests) {
        this.tests = tests;
        return this;
    }
}
