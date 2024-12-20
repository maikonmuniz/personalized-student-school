package com.university.personalizedLessons.infrastructure.operationORM;

import com.university.personalizedLessons.infrastructure.models.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpa extends JpaRepository<CourseModel, Integer> {
}
