package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.discipline.DisciplineAggregate;

import java.util.List;

public interface DisciplineRepository {
    List<DisciplineAggregate> getAllDiscipline(int courseId);
    DisciplineAggregate save (DisciplineAggregate discipline);
}
