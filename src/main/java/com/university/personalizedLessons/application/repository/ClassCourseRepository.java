package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.classCourse.ClassCourse;

public interface ClassCourseRepository {
    ClassCourse generate (ClassCourse classCourse);
}
