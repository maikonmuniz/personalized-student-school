package com.university.personalizedLessons.application.usecases.course;

import com.university.personalizedLessons.domain.entities.course.CourseAggregate;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;

import java.util.UUID;

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

        CourseAggregate course = new CourseAggregate(
                new Name(input.name),
                new Description(input.description),
                input.type_course_id,
                input.accountId
        );

        CourseAggregate courseNew = this.courseRepo.register(course);

        if (courseNew == null) throw this.exceptionAdapter.badRequest("No register course!");

        return new Output(
                courseNew.getName(),
                courseNew.getDescription(),
                courseNew.getTypeCourseId(),
                courseNew.getAccountId()
        );
    }

    public static record Input (
            String name,
            String description,
            int type_course_id,
            String accountId
    ) {}

    public static record Output (
            String name,
            String description,
            int type_course_id,
            UUID accountId
    ) {}
}
