package com.university.personalizedLessons.application.usecases.discipline;

import com.university.personalizedLessons.domain.entities.discipline.DisciplineAggregate;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.DisciplineRepo;

import java.util.List;

public class GetAllDiscipline {

    private final DisciplineRepo disciplineRepo;
    private final ExceptionAdapter exceptionAdapter;

    public GetAllDiscipline(
            DisciplineRepo disciplineRepo,
            ExceptionAdapter exceptionAdapter
    ) {
        this.disciplineRepo = disciplineRepo;
        this.exceptionAdapter = exceptionAdapter;
    }

    public Output execute (Input input) {
        List<DisciplineAggregate> listDisciplineAggregate = this.disciplineRepo.getAllDiscipline(input.courseId);
        System.out.println(listDisciplineAggregate.size());
        if (listDisciplineAggregate.isEmpty()) throw this.exceptionAdapter.badRequest("List discipline is Empty!");
        return new Output(
                listDisciplineAggregate
        );
    }

    public static record Input (
            int courseId
    ) {}

    public static record Output (List<DisciplineAggregate> listDiscipline) {}

}
