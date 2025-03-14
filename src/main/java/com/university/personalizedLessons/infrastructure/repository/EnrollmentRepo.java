package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.EnrollmentRepository;
import com.university.personalizedLessons.domain.domainServices.Enrollment;
import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.course.CourseAggregate;
import com.university.personalizedLessons.infrastructure.models.AccountCourse;
import com.university.personalizedLessons.infrastructure.models.AccountModel;
import com.university.personalizedLessons.infrastructure.models.CourseModel;
import com.university.personalizedLessons.infrastructure.models.TypeCourseModel;
import com.university.personalizedLessons.infrastructure.operationORM.EnrollmentJpa;

public class EnrollmentRepo implements EnrollmentRepository {

    private final EnrollmentJpa enrollmentJpa;

    public EnrollmentRepo (EnrollmentJpa enrollmentJpa) {
        this.enrollmentJpa = enrollmentJpa;
    }

    @Override
    public Enrollment save(Account account, CourseAggregate courseAggregate) {

        AccountModel accountModel = new AccountModel();
        accountModel.setFirstName(account.getFirstName());
        accountModel.setLastName(account.getLastName());
        accountModel.setCpf(account.getCpf());
        accountModel.setUsername(account.getUsername());
        accountModel.setPassword(account.getPassword());

        CourseModel courseModel = new CourseModel();
        courseModel.setName(courseAggregate.getName());
        courseModel.setDescription(courseAggregate.getDescription());

        TypeCourseModel typeCourseModel = new TypeCourseModel();
        typeCourseModel.setId(courseAggregate.getTypeCourseId());

        courseModel.setTypeCourse(typeCourseModel);

        AccountCourse accountCourse = new AccountCourse();
        accountCourse.setAccount(accountModel);
        accountCourse.setCourse(courseModel);

        accountCourse = this.enrollmentJpa.save(accountCourse);

        return new Enrollment(
                accountCourse.getId(),
                accountCourse.getCourse().getId(),
                accountCourse.getAccount().getId()
        );
    }
}
