package com.university.personalizedLessons.application.repository;


import com.university.personalizedLessons.domain.entities.enrollment.Enrollment;

public interface EnrollmentRepository {
    Enrollment save (Enrollment enrollment);
}
