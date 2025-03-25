package com.university.personalizedLessons.application.usecases.course;

import com.university.personalizedLessons.domain.entities.course.Course;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;

import java.util.List;

public class GetAllCourse {

    private CourseRepo courseRepo;

    public GetAllCourse (CourseRepo courseRepo) {
        this.courseRepo = courseRepo;

    }

    public Output execute (Input input) {
        List<Course> courses = this.courseRepo.findCourseAll(input.start(), input.siza());
        return new Output(courses);
    }

    public static record Input (int start, int siza) {}

    public static record Output (List<Course> courseAll) {}

}
