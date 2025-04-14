package com.university.personalizedLessons.main.classCourse;

import com.university.personalizedLessons.application.usecases.classCourse.CreateClass;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class classCourse {

    @Bean
    CreateClass createClassCourse (ExceptionAdapter exceptionAdapter) {
        return classCourse(exceptionAdapter);
    }

}
