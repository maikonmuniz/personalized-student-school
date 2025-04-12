package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.enrollmentDiscipline.EnrollmentDiscipline;

public interface AccountDisciplineRepository {
    EnrollmentDiscipline save (EnrollmentDiscipline enrollmentDiscipline);
}
