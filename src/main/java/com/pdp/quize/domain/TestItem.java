package com.pdp.quize.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Entity
public class TestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private BigInteger id;
    private BigInteger testId;
    private String question;
    private Set<Answer> answers;

    public BigInteger getId() {
        return id;
    }

    public TestItem setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public BigInteger getTestId() {
        return testId;
    }

    public TestItem setTestId(BigInteger testId) {
        this.testId = testId;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public TestItem setQuestion(String question) {
        this.question = question;
        return this;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public TestItem setAnswers(Set<Answer> answers) {
        this.answers = answers;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestItem)) return false;

        TestItem testItem = (TestItem) o;

        return id.equals(testItem.id) && testId.equals(testItem.testId)
                && question.equals(testItem.question) && answers.equals(testItem.answers);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + testId.hashCode();
        result = 31 * result + question.hashCode();
        result = 31 * result + answers.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TestItem{" + "id=" + id +
                ", testId=" + testId +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}
