package com.university.personalizedLessons.main.repository;

import com.university.personalizedLessons.infrastructure.operationORM.DisciplineJPA;
import com.university.personalizedLessons.infrastructure.repository.DisciplineRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainDiscipline {

    @Bean
    DisciplineRepo createDisciplineRepo (DisciplineJPA disciplineJPA) {
        return new DisciplineRepo(disciplineJPA);
    }
}
