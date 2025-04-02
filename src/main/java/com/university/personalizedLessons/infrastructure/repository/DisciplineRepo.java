package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.DisciplineRepository;
import com.university.personalizedLessons.domain.entities.discipline.DisciplineAggregate;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.models.DisciplineModel;
import com.university.personalizedLessons.infrastructure.operationORM.DisciplineJPA;

import java.util.ArrayList;
import java.util.List;

public class DisciplineRepo implements DisciplineRepository {

    private final DisciplineJPA operation;

    public DisciplineRepo (DisciplineJPA disciplineJPA) {
        this.operation = disciplineJPA;
    }

    @Override
    public List<DisciplineAggregate> getAllDiscipline(int courseID) {

        List<DisciplineModel> listDisciplineModel = this.operation.findAll(courseID);
        List<DisciplineAggregate> disciplineAggregatesList = new ArrayList<DisciplineAggregate>();

        for (DisciplineModel value : listDisciplineModel) {
            disciplineAggregatesList.add(new DisciplineAggregate(
                    value.getId(),
                    new Name(value.getName()),
                    new Description(value.getDescription()),
                    value.getCourse().getId()));
        }

        return disciplineAggregatesList;
    }

    @Override
    public DisciplineAggregate save(DisciplineAggregate discipline) {
        return null;
    }
}
