package com.university.personalizedLessons.main.classCourse;

import com.university.personalizedLessons.application.repository.ClassCourseRepository;
import com.university.personalizedLessons.application.usecases.classCourse.CreateClass;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainClassCourse {

    @Bean
    CreateClass createClassCourse (
            ExceptionAdapter exceptionAdapter,
            ClassCourseRepository classRepo
            ) {
        return new CreateClass(
                exceptionAdapter,
                classRepo
        );
    }
}
