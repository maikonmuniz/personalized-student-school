package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.course.Course;

import java.util.List;

public interface CourseRepository {
    Course register (Course course);
    Course findCourseId (String id);
    Course findCourse (int id);
    List<Course> findCourseAll (int start, int size);
}
