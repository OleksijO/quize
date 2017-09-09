package com.pdp.quize.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Test implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(19) unsigned", unique = true, nullable = false, updatable = false,
            insertable = false)
    private BigInteger testId;

    private LocalDate availableSince;
    private LocalDate availableUntil;
    private Integer passThreshold;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User tutor;

    @OneToMany(mappedBy = "test")
    private List<TestItem> testItems;

    @OneToMany(mappedBy = "test")
    private List<TestResult> testResults;

    public BigInteger getTestId() {
        return testId;
    }

    public Test setTestId(BigInteger testId) {
        this.testId = testId;
        return this;
    }

    public LocalDate getAvailableSince() {
        return availableSince;
    }

    public Test setAvailableSince(LocalDate availableSince) {
        this.availableSince = availableSince;
        return this;
    }

    public LocalDate getAvailableUntil() {
        return availableUntil;
    }

    public Test setAvailableUntil(LocalDate availableUntil) {
        this.availableUntil = availableUntil;
        return this;
    }

    public Integer getPassThreshold() {
        return passThreshold;
    }

    public Test setPassThreshold(Integer passThreshold) {
        this.passThreshold = passThreshold;
        return this;
    }

    public Subject getSubject() {
        return subject;
    }

    public Test setSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public User getTutor() {
        return tutor;
    }

    public Test setTutor(User tutor) {
        this.tutor = tutor;
        return this;
    }

    public List<TestItem> getTestItems() {
        return testItems;
    }

    public Test setTestItems(List<TestItem> testItems) {
        this.testItems = testItems;
        return this;
    }

    public List<TestResult> getTestResults() {
        return testResults;
    }

    public Test setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
        return this;
    }
}
