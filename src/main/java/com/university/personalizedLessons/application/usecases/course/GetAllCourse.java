package com.university.personalizedLessons.application.usecases.course;

import com.university.personalizedLessons.domain.entities.course.CourseAggregate;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;

import java.util.List;

public class GetAllCourse {

    private CourseRepo courseRepo;
    private ExceptionAdapter error;

    public GetAllCourse (
            CourseRepo courseRepo,
            ExceptionAdapter error
    ) {
        this.courseRepo = courseRepo;
        this.error = error;
    }

    public Output execute (Input input) {
        if (input.start <= 0) throw this.error.badRequest("Inicio para filtro com numero invalido!");
        if (input.siza <= 0) throw this.error.badRequest("Intervalo de filtro invalido!");
        List<CourseAggregate> courses = this.courseRepo.findCourseAll(
                input.start(),
                input.siza()
        );
        return new Output(courses);
    }

    public static record Input (int start, int siza) {}

    public static record Output (List<CourseAggregate> courseAll) {}

}
