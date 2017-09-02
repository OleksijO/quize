package com.pdp.quize.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Subject {
    @Id
    @Column(unique = true, nullable = false)
    private String name;
    private User tutor;
    private List<User> students;
    private List<Test> tests;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;

        Subject subject = (Subject) o;

        return name.equals(subject.name) && tutor.equals(subject.tutor)
                && students.equals(subject.students) && tests.equals(subject.tests);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + tutor.hashCode();
        result = 31 * result + students.hashCode();
        result = 31 * result + tests.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Subject{" + "name='" + name + '\'' +
                ", tutor=" + tutor +
                ", students=" + students +
                ", tests=" + tests +
                '}';
    }
}
