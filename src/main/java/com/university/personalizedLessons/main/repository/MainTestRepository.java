package com.university.personalizedLessons.main.repository;

import com.university.personalizedLessons.infrastructure.operationORM.ClassCourseAccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.TaskJPA;
import com.university.personalizedLessons.infrastructure.repository.TestRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainTestRepository {

    @Bean
    public TestRepo createTestRepo (
            TaskJPA taskJPA,
            ClassCourseAccountJPA classCourseAccountJPA
    ){
        return new TestRepo(taskJPA, classCourseAccountJPA);
    }
}
