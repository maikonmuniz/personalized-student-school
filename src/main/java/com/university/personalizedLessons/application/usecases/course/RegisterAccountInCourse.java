package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.course.CourseAggregate;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentRepo;

public class RegisterAccountInCourse {

    private final AccountRepo accountRepo;
    private final CourseRepo courseRepo;
    private final EnrollmentRepo enrollmentRepo;
    private final ExceptionAdapter exceptionAdapter;

    public RegisterAccountInCourse (
            AccountRepo accountRepo,
            CourseRepo courseRepo,
            EnrollmentRepo enrollmentRepo,
            ExceptionAdapter exceptionAdapter
    ) {
        this.accountRepo = accountRepo;
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;
        this.exceptionAdapter = exceptionAdapter;
    }

    public Output execute (Input input) {
        Account account = this.accountRepo.findAccount(input.username);
        if (account == null) throw this.exceptionAdapter.badRequest("There is no account!");
        CourseAggregate course = this.courseRepo.findCourse(input.idCourse);
        if (course == null) throw this.exceptionAdapter.badRequest("There is no course!");
        Enrollment enrollmentRepoRegister = this.enrollmentRepo.save (
                account,
                course
        );
        return new Output(
                enrollmentRepoRegister.getIdAccount(),
                enrollmentRepoRegister.getIdCourse()
        );
    }

    public static record Input (
            String username,
            int idCourse
    ) {}

    public static record Output (
            Long idAccount,
            int idCourse
    ) {}
}
