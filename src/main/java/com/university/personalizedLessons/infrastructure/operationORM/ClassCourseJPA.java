package com.university.personalizedLessons.infrastructure.operationORM;

import com.university.personalizedLessons.infrastructure.models.ClassCourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassCourseJPA extends JpaRepository <ClassCourseModel, Long> { }
