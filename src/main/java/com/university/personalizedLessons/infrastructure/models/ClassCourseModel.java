package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "class_course")
@Table(name = "class_course")
public class ClassCourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active_discipline")
    private boolean activeDiscipline;

    @Column(name = "date_init")
    private LocalDateTime localDateTimeInit;

    @Column(name = "date_end")
    private LocalDateTime localDateTimeEnd;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private AccountModel accountModel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public AccountModel getAccountModel() {
        return accountModel;
    }

    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public LocalDateTime getLocalDateTimeInit() {
        return localDateTimeInit;
    }

    public void setLocalDateTimeInit(LocalDateTime localDateTimeInit) {
        this.localDateTimeInit = localDateTimeInit;
    }

    public LocalDateTime getLocalDateTimeEnd() {
        return localDateTimeEnd;
    }

    public void setLocalDateTimeEnd(LocalDateTime localDateTimeEnd) {
        this.localDateTimeEnd = localDateTimeEnd;
    }

    public boolean isActiveDiscipline() {
        return activeDiscipline;
    }

    public void setActiveDiscipline(boolean activeDiscipline) {
        this.activeDiscipline = activeDiscipline;
    }
}
