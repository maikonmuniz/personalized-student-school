package com.university.personalizedLessons.infrastructure.operationORM;

import com.university.personalizedLessons.infrastructure.models.ClassCourseAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassCourseAccountJPA extends JpaRepository <ClassCourseAccountModel, Long> { }
