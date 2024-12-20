package com.university.personalizedLessons.infrastructure.operationORM;

import com.university.personalizedLessons.infrastructure.models.AccountCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentJpa extends JpaRepository<AccountCourse, Long> {
}
