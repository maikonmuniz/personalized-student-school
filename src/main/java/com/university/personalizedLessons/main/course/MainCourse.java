package com.university.personalizedLessons.main.course;

import com.university.personalizedLessons.application.usecases.course.CreateCourse;
import com.university.personalizedLessons.application.usecases.course.GetAllCourse;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainCourse {

    @Bean
    CreateCourse createUseCaseCourse (CourseRepo courseRepo) {
        return new CreateCourse(courseRepo);
    }

    @Bean
    GetAllCourse createGetAllCourse (CourseRepo courseRepo) {
        return new GetAllCourse(courseRepo);
    }
}
