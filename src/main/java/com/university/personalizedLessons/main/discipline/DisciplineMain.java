package com.university.personalizedLessons.main.discipline;

import com.university.personalizedLessons.application.usecases.discipline.GetAllDiscipline;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.DisciplineRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisciplineMain {

    @Bean
    public GetAllDiscipline createGetAll (
            DisciplineRepo disciplineRepo,
            ExceptionAdapter exceptionAdapter
    ) {
        return new GetAllDiscipline(
                disciplineRepo,
                exceptionAdapter
        );
    }
}
