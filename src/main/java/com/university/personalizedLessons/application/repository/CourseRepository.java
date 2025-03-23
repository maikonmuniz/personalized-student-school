package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.course.Course;

public interface CourseRepository {
    Course register (Course course);
    Course findCourse(int id);
}
