package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "class_course_account")
@Entity(name = "class_course_account")
public class ClassCourseAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "presence")
    private boolean presence;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private AccountModel accountModel;

    @ManyToOne
    @JoinColumn(name = "id_class_course")
    private ClassCourse classCourse;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public AccountModel getAccountModel() {
        return accountModel;
    }

    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public ClassCourse getClassCourse() {
        return classCourse;
    }

    public void setClassCourse(ClassCourse classCourse) {
        this.classCourse = classCourse;
    }
}
