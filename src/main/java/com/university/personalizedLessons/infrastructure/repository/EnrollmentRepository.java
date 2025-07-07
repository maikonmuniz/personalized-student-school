package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.EnrollmentCourseRepository;
import com.university.personalizedLessons.domain.entities.enrollmentCourse.Enrollment;
import com.university.personalizedLessons.infrastructure.models.AccountCourseModel;
import com.university.personalizedLessons.infrastructure.models.AccountModel;
import com.university.personalizedLessons.infrastructure.models.CourseModel;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.CourseJpa;
import com.university.personalizedLessons.infrastructure.operationORM.EnrollmentJpa;

import java.util.UUID;

public class EnrollmentRepository implements EnrollmentCourseRepository {

    private final EnrollmentJpa enrollmentJpa;
    private final CourseJpa courseJpa;
    private final AccountJPA accountJPA;
    public EnrollmentRepository (
            EnrollmentJpa enrollmentJpa,
            CourseJpa courseJpa,
            AccountJPA accountJPA
    ) {
        this.enrollmentJpa = enrollmentJpa;
        this.courseJpa = courseJpa;
        this.accountJPA = accountJPA;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {

        AccountCourseModel accountCourseModel = new AccountCourseModel();

        String courseID = enrollment.getCourseID();
        CourseModel courseModel = this.courseJpa.findByCourseID(courseID);
        accountCourseModel.setCourse(courseModel);

        String accountID = enrollment.getAccountID();
        AccountModel accountModel = this.accountJPA.consultAccountId(accountID);
        accountCourseModel.setAccount(accountModel);

        accountCourseModel.setIdCourseDiscipline(UUID.fromString(enrollment.getId()));
        accountCourseModel = enrollmentJpa.save(accountCourseModel);

        String enrollmentID = accountCourseModel.getIdCourseDiscipline();

        Enrollment enrollmentSave = new Enrollment(
                enrollmentID,
                accountID,
                courseID
        );

        return enrollmentSave;
    }
}
