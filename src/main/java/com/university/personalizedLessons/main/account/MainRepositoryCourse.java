package com.university.personalizedLessons.main.account;

import com.university.personalizedLessons.infrastructure.operationORM.CourseJpa;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainRepositoryCourse {

    @Bean
    public CourseRepo instanceCourseRepository(CourseJpa courseJpa) {
        return new CourseRepo(courseJpa);
    }
}
