package com.pdp.quize.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(19) unsigned", unique = true, nullable = false, updatable = false,
            insertable = false)
    private BigInteger answerId;

    private String text;
    private Boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_item_id", nullable = false)
    private TestItem testItem;

    public BigInteger getAnswerId() {
        return answerId;
    }

    public Answer setAnswerId(BigInteger answerId) {
        this.answerId = answerId;
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

    public TestItem getTestItem() {
        return testItem;
    }

    public Answer setTestItem(TestItem testItem) {
        this.testItem = testItem;
        return this;
    }
}
