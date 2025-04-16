package com.university.personalizedLessons.application.usecases.classCourse;

import com.university.personalizedLessons.application.repository.AccountRepository;
import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;

public class AcceptClass {

    private ExceptionAdapter exception;
    private AccountRepository accountRepo;

    public AcceptClass (
            ExceptionAdapter exception,
            AccountRepository accountRepo
    ) {
        this.exception = exception;
        this.accountRepo = accountRepo;
    }

    public Output execute (Input input) {
        if (input.studentID.isEmpty()) throw this.exception.badRequest("Field is empty!");
        if (input.classID.isEmpty()) throw this.exception.badRequest("Field is empty!");

        Account account = this.accountRepo.findOneId(input.studentID);

        if (account == null) throw this.exception.badRequest("No exist account");

        boolean validationIfAccountStudent = account.validationStudent();

        if (!validationIfAccountStudent) throw this.exception.badRequest("No permission account for accept class!");

        return new Output(input.studentID, input.classID);
    }

    public static record Input (
            String classID,
            String studentID
    ) {}

    public static record Output (
            String classID,
            String studentID
    ) {}
}
