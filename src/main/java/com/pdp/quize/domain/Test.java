package com.pdp.quize.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private BigInteger id;
    private Subject subject;
    private List<TestItem> testItems;
    private User tutor;
    private LocalDate availableSince;
    private LocalDate availableUntil;
    private Integer passThreshold;

    public BigInteger getId() {
        return id;
    }

    public Test setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public Subject getSubject() {
        return subject;
    }

    public Test setSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public List<TestItem> getTestItems() {
        return testItems;
    }

    public Test setTestItems(List<TestItem> testItems) {
        this.testItems = testItems;
        return this;
    }

    public User getTutor() {
        return tutor;
    }

    public Test setTutor(User tutor) {
        this.tutor = tutor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test)) return false;

        Test test = (Test) o;

        return id.equals(test.id) && subject.equals(test.subject) && testItems.equals(test.testItems)
                && tutor.equals(test.tutor) && availableSince.equals(test.availableSince)
                && availableUntil.equals(test.availableUntil) && passThreshold.equals(test.passThreshold);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + subject.hashCode();
        result = 31 * result + testItems.hashCode();
        result = 31 * result + tutor.hashCode();
        result = 31 * result + availableSince.hashCode();
        result = 31 * result + availableUntil.hashCode();
        result = 31 * result + passThreshold.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Test{" + "id=" + id +
                ", subject=" + subject +
                ", testItems=" + testItems +
                ", tutor=" + tutor +
                ", availableSince=" + availableSince +
                ", availableUntil=" + availableUntil +
                ", passThreshold=" + passThreshold +
                '}';
    }
}
