package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.domainServices.Enrollment;
import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.course.Course;

public interface EnrollmentRepository {
    Enrollment save (Account account, Course courseAggregate);
}
