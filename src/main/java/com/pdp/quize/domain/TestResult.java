package com.pdp.quize.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private BigInteger id;
    private User student;
    private Test test;
    private Integer attemptNumber;
    private LocalDateTime lastPassingDate;
    private Integer averageResult;

    public BigInteger getId() {
        return id;
    }

    public TestResult setId(BigInteger id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestResult)) return false;

        TestResult that = (TestResult) o;

        return id.equals(that.id) && student.equals(that.student) && test.equals(that.test)
                && attemptNumber.equals(that.attemptNumber) && lastPassingDate.equals(that.lastPassingDate)
                && averageResult.equals(that.averageResult);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + student.hashCode();
        result = 31 * result + test.hashCode();
        result = 31 * result + attemptNumber.hashCode();
        result = 31 * result + lastPassingDate.hashCode();
        result = 31 * result + averageResult.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TestResult{" + "id=" + id +
                ", student=" + student +
                ", test=" + test +
                ", attemptNumber=" + attemptNumber +
                ", lastPassingDate=" + lastPassingDate +
                ", averageResult=" + averageResult +
                '}';
    }
}
