package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.DisciplineRepository;
import com.university.personalizedLessons.domain.entities.discipline.DisciplineAggregate;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.models.AccountModel;
import com.university.personalizedLessons.infrastructure.models.CourseModel;
import com.university.personalizedLessons.infrastructure.models.DisciplineModel;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.CourseJpa;
import com.university.personalizedLessons.infrastructure.operationORM.DisciplineJPA;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DisciplineRepo implements DisciplineRepository {

    private final DisciplineJPA operationDisciplineJPA;
    private final CourseJpa operationCourseJPA;
    private final AccountJPA operationAccountJPA;

    public DisciplineRepo (
            DisciplineJPA disciplineJPA,
            CourseJpa operationCourseJPA,
            AccountJPA operationAccountJPA
    ) {
        this.operationDisciplineJPA = disciplineJPA;
        this.operationCourseJPA = operationCourseJPA;
        this.operationAccountJPA = operationAccountJPA;
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
                    value.getAccountModel().getIdAccount(),
                    value.getCourse().getId()));
        }

        return disciplineAggregatesList;
    }

    @Override
    public DisciplineAggregate save(DisciplineAggregate discipline) {

        DisciplineModel disciplineModel = new DisciplineModel();

        var disciplineID = discipline.getDisciplineID();

        disciplineModel.setDisciplineID(disciplineID);

        int courseID = discipline.getCourseID();

        Optional<CourseModel> courseModel = this.operationCourseJPA.findById(courseID);
        System.out.println("222222222222222222222222222222");
        System.out.println("222222222222222222222222222222");
        System.out.println("222222222222222222222222222222");
        System.out.println("222222222222222222222222222222");
        System.out.println("222222222222222222222222222222");
        System.out.println("222222222222222222222222222222");
        System.out.println("222222222222222222222222222222");
        System.out.println("222222222222222222222222222222");
        System.out.println("222222222222222222222222222222");
        System.out.println("222222222222222222222222222222");
        System.out.println("222222222222222222222222222222");
        System.out.println("222222222222222222222222222222");
        System.out.println("222222222222222222222222222222");
        System.out.println(courseModel.get().getId());
        disciplineModel.setCourse(courseModel.orElse(null));

        String accountID = discipline.getAccountID();
        AccountModel accountModel = this.operationAccountJPA.consultAccountId(accountID);

        disciplineModel.setAccountModel(accountModel);

        disciplineModel.setName(discipline.getName());

        String description = discipline.getDescription();
        disciplineModel.setDescription(description);
        disciplineModel = this.operationDisciplineJPA.save(disciplineModel);

        DisciplineAggregate disciplineEntity = new DisciplineAggregate (
                disciplineModel.getId(),
                disciplineModel.getDisciplineID(),
                new Name(disciplineModel.getName()),
                new Description(disciplineModel.getDescription()),
                disciplineModel.getAccountModel().getIdAccount(),
                courseModel.get().getId()
        );

        return disciplineEntity;
    }
}
