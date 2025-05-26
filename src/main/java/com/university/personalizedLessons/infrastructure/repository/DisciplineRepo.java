package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.DisciplineRepository;
import com.university.personalizedLessons.domain.entities.discipline.DisciplineAggregate;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.models.CourseModel;
import com.university.personalizedLessons.infrastructure.models.DisciplineModel;
import com.university.personalizedLessons.infrastructure.operationORM.CourseJpa;
import com.university.personalizedLessons.infrastructure.operationORM.DisciplineJPA;

import java.util.ArrayList;
import java.util.List;

public class DisciplineRepo implements DisciplineRepository {

    private final DisciplineJPA operationDisciplineJPA;
    private final CourseJpa operationCourseJPA;

    public DisciplineRepo (
            DisciplineJPA disciplineJPA,
            CourseJpa operationCourseJPA
    ) {
        this.operationDisciplineJPA = disciplineJPA;
        this.operationCourseJPA = operationCourseJPA;
    }

    @Override
    public List<DisciplineAggregate> getAllDiscipline(int courseID) {

        List<DisciplineModel> listDisciplineModel = this.operationDisciplineJPA.findAll(courseID);
        List<DisciplineAggregate> disciplineAggregatesList = new ArrayList<DisciplineAggregate>();

        for (DisciplineModel value : listDisciplineModel) {
            disciplineAggregatesList.add(new DisciplineAggregate(
                    value.getId(),
                    value.getDisciplineID(),
                    new Name(value.getName()),
                    new Description(value.getDescription()),
                    value.getCourse().getId()));
        }

        return disciplineAggregatesList;
    }

    @Override
    public DisciplineAggregate save(DisciplineAggregate discipline) {

        DisciplineModel disciplineModel = new DisciplineModel();
        disciplineModel.setDisciplineID(discipline.getDisciplineID());
        String courseID = String.valueOf(discipline.getCourseID());
        CourseModel courseModel = this.operationCourseJPA.findByCourseID(courseID);
        disciplineModel.setCourse(courseModel);
        String description = discipline.getDescription();
        disciplineModel.setDescription(description);
        disciplineModel = this.operationDisciplineJPA.save(disciplineModel);

        DisciplineAggregate disciplineEntity = new DisciplineAggregate (
                disciplineModel.getId(),
                disciplineModel.getDisciplineID(),
                new Name(disciplineModel.getName()),
                new Description(disciplineModel.getDescription()),
                courseModel.getId()
        );

        return disciplineEntity;
    }
}
