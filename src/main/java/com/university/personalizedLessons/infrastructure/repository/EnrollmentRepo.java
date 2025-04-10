package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.EnrollmentRepository;
import com.university.personalizedLessons.domain.entities.enrollment.Enrollment;
import com.university.personalizedLessons.infrastructure.models.AccountCourseModel;
import com.university.personalizedLessons.infrastructure.models.AccountModel;
import com.university.personalizedLessons.infrastructure.models.CourseModel;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.CourseJpa;
import com.university.personalizedLessons.infrastructure.operationORM.EnrollmentJpa;

public class EnrollmentRepo implements EnrollmentRepository {

    private final EnrollmentJpa enrollmentJPA;
    private final AccountJPA accountJPA;
    private final CourseJpa courseJPA;

    public EnrollmentRepo (
            EnrollmentJpa enrollmentJPA,
            AccountJPA accountJPA,
            CourseJpa courseJPA
    ) {
        this.enrollmentJPA = enrollmentJPA;
        this.accountJPA = accountJPA;
        this.courseJPA = courseJPA;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {

        String accountID = enrollment.getAccountID().toString();
        AccountModel accountModel = this.accountJPA.consultAccountId(accountID);

        String courseID = enrollment.getCourseID().toString();
        CourseModel courseModel = this.courseJPA.findByCourseID(courseID);

        AccountCourseModel accountCourse = new AccountCourseModel();
        accountCourse.setIdCourseDiscipline(enrollment.getId());
        accountCourse.setAccount(accountModel);
        accountCourse.setCourse(courseModel);

        this.enrollmentJPA.save(accountCourse);

        return enrollment;
    }
}
