package com.university.personalizedLessons.infrastructure.operationORM;

import com.university.personalizedLessons.infrastructure.models.ClassCourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassCourseJPA extends JpaRepository <ClassCourseModel, Long> {
    @Query(value = "SELECT * FROM class_course c where c.class_id = :id;", nativeQuery = true)
    ClassCourseModel consultClassId(@Param("id") String id);
}
