package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.RegisterCourse.Enrollment;
import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.course.CourseAggregate;

public interface EnrollmentRepository {
    Enrollment save (Account account, CourseAggregate courseAggregate);
}
