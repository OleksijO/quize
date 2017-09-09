package com.pdp.quize.domain;

import com.pdp.quize.constant.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(19) unsigned", unique = true, nullable = false, updatable = false,
            insertable = false)
    private BigInteger userId;

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "student")
    private List<TestResult> testResults;

    @OneToMany(mappedBy = "tutor")
    private List<Test> tests;

    @ManyToMany
    @JoinTable(joinColumns = {
            @JoinColumn(name = "subject_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)})
    private List<Subject> subjects;

    public BigInteger getUserId() {
        return userId;
    }

    public User setUserId(BigInteger userId) {
        this.userId = userId;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<TestResult> getTestResults() {
        return testResults;
    }

    public User setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
        return this;
    }

    public List<Test> getTests() {
        return tests;
    }

    public User setTests(List<Test> tests) {
        this.tests = tests;
        return this;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public User setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        return this;
    }
}
