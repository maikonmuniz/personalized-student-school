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

        if (input.name == null) throw this.exceptionAdapter.badRequest("Field name is Empty!");
        if (input.description == null) throw this.exceptionAdapter.badRequest("Field description is Empty!");

        Course course = new Course(
                input.name,
                input.description,
                input.type_course_id
        );

        Course courseNew = this.courseRepo.register(course);

        if (courseNew == null){
            throw this.exceptionAdapter.badRequest("No register course!");
        }

        return new Output(
                courseNew.getName(),
                courseNew.getDescription(),
                courseNew.getTypeCourseId()
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
