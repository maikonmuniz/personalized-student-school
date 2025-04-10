package com.university.personalizedLessons.application.usecases.course;

import com.university.personalizedLessons.domain.entities.enrollment.Enrollment;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentRepo;

import java.time.LocalDate;

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

        if (input.accountID == null || input.accountID == "") throw this.exceptionAdapter.badRequest("There is no account!");
        if (input.courseID == null) throw this.exceptionAdapter.badRequest("There is no course!");

        Enrollment enrollment = new Enrollment(
                input.accountID,
                input.courseID
        );

        enrollment = this.enrollmentRepo.save(enrollment);

        return new Output(
                enrollment.getId().toString(),
                enrollment.getAccountID().toString(),
                enrollment.getCourseID().toString(),
                enrollment.getDateCurrent()
        );
    }

    public static record Input (
            String accountID,
            String courseID
    ) {}

    public static record Output (
            String id,
            String accountID,
            String courseID,
            LocalDate dateCurrent
    ) {}
}
