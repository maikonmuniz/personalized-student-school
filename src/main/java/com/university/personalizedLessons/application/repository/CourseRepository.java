package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.course.CourseAggregate;

import java.util.List;

public interface CourseRepository {
    CourseAggregate register (CourseAggregate course);
    CourseAggregate findCourse(int id);
    List<CourseAggregate> findCourseAll(int start, int size);
}
