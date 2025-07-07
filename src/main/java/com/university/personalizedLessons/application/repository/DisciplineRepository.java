package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.discipline.Discipline;

import java.util.List;

public interface DisciplineRepository {
    List<Discipline> getAllDiscipline(int courseId);
    Discipline save (Discipline discipline);
}
