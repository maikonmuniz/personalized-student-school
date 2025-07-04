package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discipline")
public class DisciplineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "discipline_id", length = 36, unique = true)
    private String disciplineID;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 250)
    private String description;

    @Column(name = "active_discipline")
    private boolean activeDiscipline;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private CourseModel course;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id_account", nullable = false)
    private AccountModel accountModel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }

    public String getDisciplineID() {
        return disciplineID;
    }

    public void setDisciplineID(String disciplineID) {
        this.disciplineID = disciplineID;
    }

    public boolean isActiveDiscipline() {
        return activeDiscipline;
    }

    public void setActiveDiscipline(boolean activeDiscipline) {
        this.activeDiscipline = activeDiscipline;
    }

    public AccountModel getAccountModel() {
        return accountModel;
    }

    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }
}

