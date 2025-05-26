package com.university.personalizedLessons.application.usecases.discipline;

import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.DisciplineRepo;

public class CreateDiscipline {

    private final ExceptionAdapter exceptionAdapter;
    private final DisciplineRepo disciplineRepo;

    public CreateDiscipline (
            ExceptionAdapter exceptionAdapter,
            DisciplineRepo disciplineRepo
    ) {
        this.exceptionAdapter = exceptionAdapter;
        this.disciplineRepo = disciplineRepo;
    }

    public Output execute (Input input) {
        if (input.name == "") throw this.exceptionAdapter.badRequest("Field name no exist!");
        if (input.description == "") throw this.exceptionAdapter.badRequest("Field description no exist!");
        return new Output("Register discipline with successfully");
    }

    public static record Output (
            String messagem
    ) {}

    public static record Input (
            String name,
            String description
    ) {}
}
