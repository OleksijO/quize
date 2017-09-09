package com.pdp.quize.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
public class TestResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(19) unsigned", unique = true, nullable = false, updatable = false,
            insertable = false)
    private BigInteger testResultId;

    private Integer attemptNumber;
    private LocalDateTime lastPassingDate;
    private Integer averageResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    public BigInteger getTestResultId() {
        return testResultId;
    }

    public TestResult setTestResultId(BigInteger testResultId) {
        this.testResultId = testResultId;
        return this;
    }

    public Integer getAttemptNumber() {
        return attemptNumber;
    }

    public TestResult setAttemptNumber(Integer attemptNumber) {
        this.attemptNumber = attemptNumber;
        return this;
    }

    public LocalDateTime getLastPassingDate() {
        return lastPassingDate;
    }

    public TestResult setLastPassingDate(LocalDateTime lastPassingDate) {
        this.lastPassingDate = lastPassingDate;
        return this;
    }

    public Integer getAverageResult() {
        return averageResult;
    }

    public TestResult setAverageResult(Integer averageResult) {
        this.averageResult = averageResult;
        return this;
    }

    public User getStudent() {
        return student;
    }

    public TestResult setStudent(User student) {
        this.student = student;
        return this;
    }

    public Test getTest() {
        return test;
    }

    public TestResult setTest(Test test) {
        this.test = test;
        return this;
    }
}
