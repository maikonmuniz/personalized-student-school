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

    @Column(name = "account_discipline_id", length = 36, unique = true)
    private String accountDisciplineId;

    @ManyToOne
    @JoinColumn(name = "discipline_id_fk", referencedColumnName = "discipline_id", nullable = false)
    private DisciplineModel discipline;

    @ManyToOne
    @JoinColumn(name = "account_id_fk", referencedColumnName = "id_account", nullable = false)
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

    public String getAccountDisciplineId() {
        return accountDisciplineId;
    }

    public void setAccountDisciplineId(String accountDisciplineId) {
        this.accountDisciplineId = accountDisciplineId;
    }
}
