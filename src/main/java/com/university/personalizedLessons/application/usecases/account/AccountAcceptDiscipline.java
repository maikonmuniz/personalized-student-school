package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;

public class AccountAcceptDiscipline {

    private final ExceptionAdapter exceptionAdapter;

    public AccountAcceptDiscipline (
            ExceptionAdapter exceptionAdapter

    ) {
        this.exceptionAdapter = exceptionAdapter;
    }

    public Output execute (Input input) {
        if (input.accountID.isEmpty()) throw this.exceptionAdapter.badRequest("Field account id is empty!");
        if (input.courseID.isEmpty()) throw this.exceptionAdapter.badRequest("Field course id is empty!");
        if (input.disciplineID.isEmpty()) throw  this.exceptionAdapter.badRequest("Field discipline id is empty!");

        return new Output(
                input.courseID,
                input.accountID,
                "Selection disciplines finish!");
    }

    public static record Input (
            String courseID,
            String accountID,
            String disciplineID
            ) {}

    public static record Output (
            String courseID,
            String accountID,
            String message
    ) {}
}
