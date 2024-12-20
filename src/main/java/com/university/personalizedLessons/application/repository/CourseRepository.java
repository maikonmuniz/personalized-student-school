package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.course.CourseAggregate;

public interface CourseRepository {
    CourseAggregate findCourse(int id);
}
