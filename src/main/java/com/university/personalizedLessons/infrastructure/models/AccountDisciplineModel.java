package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "account_discipline")
@Table(name = "account_discipline")
public class AccountDisciplineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_discipline", nullable = false)
    private DisciplineModel discipline;

    @ManyToOne
    @JoinColumn(name = "id_account_course", nullable = false)
    private AccountCourseModel accountCourse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DisciplineModel getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineModel discipline) {
        this.discipline = discipline;
    }

    public AccountCourseModel getAccountCourse() {
        return accountCourse;
    }

    public void setAccountCourse(AccountCourseModel accountCourse) {
        this.accountCourse = accountCourse;
    }
}
