package com.university.personalizedLessons.application.usecases.classCourse;

import com.university.personalizedLessons.application.repository.AccountRepository;
import com.university.personalizedLessons.application.repository.ClassCourseAccountRepository;
import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.classCourse.ClassPresence;
import com.university.personalizedLessons.domain.entities.classCourse.vo.Presence;
import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;

public class AcceptClass {

    private ExceptionAdapter exception;
    private AccountRepository accountRepo;
    private ClassCourseAccountRepository classCourseAccountRepo;

    public AcceptClass (
            ExceptionAdapter exception,
            AccountRepository accountRepository,
            ClassCourseAccountRepository classCourseAccountRepo
    ) {
        this.exception = exception;
        this.accountRepo = accountRepository;
        this.classCourseAccountRepo = classCourseAccountRepo;
    }

    public Output execute (Input input) {

        if (input.studentID.isEmpty()) throw this.exception.badRequest("Field is empty!");
        if (input.classID.isEmpty()) throw this.exception.badRequest("Field is empty!");

        Account account = this.accountRepo.findOneId(input.studentID);

        if (account == null) throw this.exception.badRequest("No exist account");

        boolean validationIfAccountStudent = account.validationStudent();

        if (!validationIfAccountStudent) throw this.exception.badRequest("No permission account for accept class!");

        ClassPresence classPresence = new ClassPresence(
                new CryptoID(""),
                new CryptoID(input.classID),
                new CryptoID(input.studentID),
                new Presence(true)
        );

        ClassPresence classPresenceSave = this.classCourseAccountRepo.save(classPresence);

        if (classPresenceSave == null) throw this.exception.badRequest("No possible accept class");

        return new Output(
                classPresenceSave.getClassId(),
                classPresenceSave.getStudentID()
        );
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
