package com.university.personalizedLessons.main.course;

import com.university.personalizedLessons.infrastructure.operationORM.CourseJpa;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainCourse {

    @Bean
    CourseRepo createCourse (CourseJpa courseJpa) {
        return new CourseRepo(courseJpa);
    }

}
