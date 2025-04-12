package com.university.personalizedLessons.application.repository;


import com.university.personalizedLessons.domain.entities.enrollmentCourse.Enrollment;

public interface EnrollmentRepository {
    Enrollment save (Enrollment enrollment);
}
