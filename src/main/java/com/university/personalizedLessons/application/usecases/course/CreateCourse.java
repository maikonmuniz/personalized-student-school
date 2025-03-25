package com.university.personalizedLessons.application.usecases.course;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.course.Course;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;

public class CreateCourse {

    private CourseRepo courseRepo;

    public CreateCourse(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Output execute (Input input) {

        Course course = new Course(
                input.name,
                input.description,
                input.type_course_id
        );

        Course accountNew = this.courseRepo.register(course);

        return new Output(input.name(), input.description, input.type_course_id);
    }

    static record Input (
            String name,
            String description,
            int type_course_id
    ) {}

    static record Output (
            String name,
            String description,
            int type_course_id
    ) {}
}
