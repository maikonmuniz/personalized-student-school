package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.domain.entities.enrollmentDiscipline.EnrollmentDiscipline;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentDisciplineRepo;

public class AccountAcceptDiscipline {

    private ExceptionAdapter exceptionAdapter;
    private EnrollmentDisciplineRepo enrollmentDisciplineRepo;

    public AccountAcceptDiscipline (
            ExceptionAdapter exceptionAdapter,
            EnrollmentDisciplineRepo enrollmentDisciplineRepo
    ) {
        this.exceptionAdapter = exceptionAdapter;
        this.enrollmentDisciplineRepo = enrollmentDisciplineRepo;
    }

    public Output execute (Input input) {

        if (input.accountID.isEmpty()) throw this.exceptionAdapter.badRequest("Field account id is empty!");
        if (input.disciplineID.isEmpty()) throw this.exceptionAdapter.badRequest("Field discipline id is empty!");

        EnrollmentDiscipline enrollmentDiscipline = new EnrollmentDiscipline(
                input.accountID,
                input.disciplineID
        );

        EnrollmentDiscipline enrollmentDisciplineNew = this.enrollmentDisciplineRepo.save(enrollmentDiscipline);

        return new Output(
                enrollmentDisciplineNew.getDisciplineID(),
                enrollmentDisciplineNew.getAccountID(),
                "Selection disciplines finish!");
    }

    public static record Input (
            String accountID,
            String disciplineID
            ) {}

    public static record Output (
            String disciplineID,
            String accountID,
            String message
    ) {}
}
