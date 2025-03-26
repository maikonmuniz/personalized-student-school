package com.university.personalizedLessons.main.course;

import com.university.personalizedLessons.application.usecases.course.CreateCourse;
import com.university.personalizedLessons.application.usecases.course.GetAllCourse;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainCourse {

    @Bean
    CreateCourse createUseCaseCourse (
            CourseRepo courseRepo,
            ExceptionAdapter exceptionAdapter
    ) {
        return new CreateCourse(
                courseRepo,
                exceptionAdapter
        );
    }

    @Bean
    GetAllCourse createUsecaseGetAllCourse(
            CourseRepo courseRepo,
            ExceptionAdapter exceptionAdapter
    ) {
        return new GetAllCourse(
                courseRepo,
                exceptionAdapter
        );
    }
}
