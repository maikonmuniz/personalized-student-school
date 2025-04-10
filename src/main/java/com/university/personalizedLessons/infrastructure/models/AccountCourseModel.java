package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account_course")
@Table(name = "account_course")
public class AccountCourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_account_course", length = 36)
    private String idCourseDiscipline;

    @ManyToOne
    @JoinColumn(name = "course_id_fk", referencedColumnName = "course_id", nullable = false)
    private CourseModel course;

    @ManyToOne
    @JoinColumn(name = "account_id_fk", referencedColumnName = "id_account", nullable = false)
    private AccountModel account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdCourseDiscipline(UUID id) {
        this.idCourseDiscipline = id.toString();
    }

    public String getIdCourseDiscipline(){
        return this.idCourseDiscipline;
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