package com.university.personalizedLessons.application.usecases.discipline;

import com.university.personalizedLessons.domain.entities.discipline.DisciplineAggregate;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.DisciplineRepo;

import java.util.Objects;

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
        if (Objects.equals(input.name, "")) throw this.exceptionAdapter.badRequest("Field name no exist!");
        if (Objects.equals(input.description, "")) throw this.exceptionAdapter.badRequest("Field description no exist!");
        if (Objects.equals(input.accountID, "")) throw this.exceptionAdapter.badRequest("Field account id no exist!");
        if (input.courseID <= 0) throw this.exceptionAdapter.badRequest("Field course id is empty!");
        DisciplineAggregate discipline = new DisciplineAggregate(
                new Name(input.name),
                new Description(input.description),
                input.accountID,
                input.courseID
        );
        discipline = disciplineRepo.save(discipline);
        if (discipline == null) throw this.exceptionAdapter.badRequest("No possible register data the discipline!");
        return new Output(
                "Register discipline with successfully",
                discipline
        );
    }

    public static record Output (
            String messagem,
            DisciplineAggregate discipline
    ) {}

    public static record Input (
            String name,
            String description,
            String accountID,
            int courseID
    ) {}
}
