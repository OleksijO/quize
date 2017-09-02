package com.pdp.quize.domain;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private BigInteger id;
    private BigInteger testItemId;
    private String text;
    private Boolean isCorrect;

    public BigInteger getId() {
        return id;
    }

    public Answer setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public BigInteger getTestItemId() {
        return testItemId;
    }

    public Answer setTestItemId(BigInteger testItemId) {
        this.testItemId = testItemId;
        return this;
    }

    public String getText() {
        return text;
    }

    public Answer setText(String text) {
        this.text = text;
        return this;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public Answer setCorrect(Boolean correct) {
        isCorrect = correct;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;

        Answer answer = (Answer) o;

        return id.equals(answer.id) && testItemId.equals(answer.testItemId)
                && text.equals(answer.text) && isCorrect.equals(answer.isCorrect);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + testItemId.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + isCorrect.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Answer{" + "id=" + id +
                ", testItemId=" + testItemId +
                ", text='" + text + '\'' +
                ", isCorrect=" + isCorrect +
                ", correct=" + getCorrect() +
                '}';
    }
}
