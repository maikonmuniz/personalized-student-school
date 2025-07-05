package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.domain.entities.enrollmentCourse.Enrollment;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentRepository;

public class CourseRegistration {

    private final ExceptionAdapter error;
    private final EnrollmentRepository enrollmentRepo;

    public CourseRegistration (
            ExceptionAdapter error,
            EnrollmentRepository enrollmentRepo
    ) {
        this.error = error;
        this.enrollmentRepo = enrollmentRepo;
    }

    public Output execute (Input input) {
        if (input.accountID.equals("")) throw this.error.badRequest("No field account id!");
        if (input.courseID.equals("")) throw this.error.badRequest("No field course id!");
        Enrollment enrollment = new Enrollment(
                input.courseID,
                input.accountID
        );
        enrollment = this.enrollmentRepo.save(enrollment);
        if (enrollment == null) throw this.error.badRequest("No save data!");
        return new Output(enrollment.getCourseID(), enrollment.getAccountID());
    }

    public static record Output (String courseID, String accountID) {}
    public static record Input (String courseID, String accountID) {}
}
