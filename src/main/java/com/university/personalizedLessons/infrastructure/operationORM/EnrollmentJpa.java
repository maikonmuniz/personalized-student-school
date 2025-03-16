package com.university.personalizedLessons.infrastructure.operationORM;

import com.university.personalizedLessons.infrastructure.models.AccountCourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentJpa extends JpaRepository<AccountCourseModel, Long> {
}
