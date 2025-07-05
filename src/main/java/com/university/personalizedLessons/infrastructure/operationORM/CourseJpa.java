package com.university.personalizedLessons.infrastructure.operationORM;

import com.university.personalizedLessons.infrastructure.models.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseJpa extends JpaRepository<CourseModel, Integer> {
    @Query(value = "SELECT * FROM course c LIMIT :start OFFSET :size", nativeQuery = true)
    List<CourseModel> findAllCourseModel(@Param("start") int start, @Param("size") int size);

    @Query(value = "SELECT * FROM course c where c.course_id = ''", nativeQuery = true)
    CourseModel consultCourseID(@Param("id") String id);

    CourseModel findByCourseID(String id);
}
