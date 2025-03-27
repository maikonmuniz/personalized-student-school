package com.university.personalizedLessons.application.usecases.course;

import com.university.personalizedLessons.domain.entities.course.Course;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;

public class CreateCourse {

    private CourseRepo courseRepo;
    private ExceptionAdapter exceptionAdapter;

    public CreateCourse(
            CourseRepo courseRepo,
            ExceptionAdapter exceptionAdapter
    ) {
        this.courseRepo = courseRepo;
        this.exceptionAdapter = exceptionAdapter;
    }

    public Output execute (Input input) {

        if (input == null) throw this.exceptionAdapter.badRequest("Fields is Empty!");

        Course course = new Course(
                input.name,
                input.description,
                input.type_course_id
        );

        Course accountNew = this.courseRepo.register(course);

        return new Output(
                accountNew.getName(),
                accountNew.getDescription(),
                accountNew.getTypeCourseId()
        );
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
