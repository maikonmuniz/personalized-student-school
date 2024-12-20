package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;

@Entity
@Table(name = "account_course")
public class AccountCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private CourseModel course;

    @ManyToOne
    @JoinColumn(name = "id_account", nullable = false)
    private AccountModel account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.account = account;
    }
}