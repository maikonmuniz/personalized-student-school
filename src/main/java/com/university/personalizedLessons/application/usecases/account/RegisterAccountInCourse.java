package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.course.CourseAggregate;
import com.university.personalizedLessons.domain.domainServices.Enrollment;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentRepo;

public class RegisterAccountInCourse {

    private final AccountRepo accountRepo;
    private final CourseRepo courseRepo;
    private final EnrollmentRepo enrollmentRepo;

    public RegisterAccountInCourse (
            AccountRepo accountRepo,
            CourseRepo courseRepo,
            EnrollmentRepo enrollmentRepo
    ) {
        this.accountRepo = accountRepo;
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    public Output execute (Input input) {
        Account account = this.accountRepo.findAccount(input.username);
        if (account == null) throw new IllegalArgumentException("There is no account!");
        CourseAggregate course = this.courseRepo.findCourse(input.idCourse);
        if (course == null) throw new IllegalArgumentException("There is no course!");
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
