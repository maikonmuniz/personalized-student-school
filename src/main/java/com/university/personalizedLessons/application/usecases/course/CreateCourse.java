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

        Course CourseNew = this.courseRepo.register(course);

        if (CourseNew == null) throw  this.exceptionAdapter.badRequest("No register course!");

        return new Output(
                CourseNew.getName(),
                CourseNew.getDescription(),
                CourseNew.getTypeCourseId()
        );
    }

    public static record Input (
            String name,
            String description,
            int type_course_id
    ) {}

    public static record Output (
            String name,
            String description,
            int type_course_id
    ) {}
}
