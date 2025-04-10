package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "course")
@Table(name = "course")
public class CourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_id", nullable = false, unique = true)
    private String courseID;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 250)
    private String description;

    @ManyToOne
    @JoinColumn(name = "type_course_id")
    private TypeCourseModel typeCourse;

    @ManyToOne
    @JoinColumn(name = "id_account_fk", referencedColumnName = "id_account")
    private AccountModel accountModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeCourseModel getTypeCourse() {
        return typeCourse;
    }

    public void setTypeCourse(TypeCourseModel typeCourse) {
        this.typeCourse = typeCourse;
    }

    public AccountModel getAccountModel() {
        return accountModel;
    }

    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}
