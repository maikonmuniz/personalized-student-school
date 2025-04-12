package com.university.personalizedLessons.application.repository;


import com.university.personalizedLessons.domain.entities.enrollment.Enrollment;

public interface rollmentRepository {
    Enrollment save (Enrollment enrollment);
}
