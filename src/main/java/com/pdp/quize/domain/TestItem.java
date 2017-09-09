package com.pdp.quize.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

@Entity
public class TestItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(19) unsigned", unique = true, nullable = false, updatable = false,
            insertable = false)
    private BigInteger testItemId;

    private String question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @OneToMany(mappedBy = "testItem")
    private Set<Answer> answers;

    public BigInteger getTestItemId() {
        return testItemId;
    }

    public TestItem setTestItemId(BigInteger testItemId) {
        this.testItemId = testItemId;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public TestItem setQuestion(String question) {
        this.question = question;
        return this;
    }

    public Test getTest() {
        return test;
    }

    public TestItem setTest(Test test) {
        this.test = test;
        return this;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public TestItem setAnswers(Set<Answer> answers) {
        this.answers = answers;
        return this;
    }
}
