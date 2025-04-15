package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.ClassCourseRepository;
import com.university.personalizedLessons.domain.entities.classCourse.ClassCourse;
import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.models.AccountModel;

import com.university.personalizedLessons.infrastructure.models.ClassCourseModel;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.ClassCourseJPA;

import java.time.LocalDateTime;

public class ClassCourseRepo implements ClassCourseRepository {

    private ClassCourseJPA classCourseJPA;
    private AccountJPA accountJPA;

    public ClassCourseRepo (
            ClassCourseJPA classCourseJPA,
            AccountJPA accountJPA
    ) {
        this.classCourseJPA = classCourseJPA;
        this.accountJPA = accountJPA;
    }

    @Override
    public ClassCourse generate(ClassCourse classCourse) {

        AccountModel accountModel = this.accountJPA.consultAccountId(classCourse.getTeacherId());

        ClassCourseModel classCourseModel = new ClassCourseModel();

        String nameValue = classCourse.getName();
        String descriptionValue = classCourse.getDescription();
        LocalDateTime dateValue = classCourseModel.getLocalDateTimeInit();

        classCourseModel.setAccountModel(accountModel);
        classCourseModel.setName(nameValue);
        classCourseModel.setDescription(descriptionValue);
        classCourseModel.setLocalDateTimeInit(dateValue);

        classCourseModel = this.classCourseJPA.save(classCourseModel);

        ClassCourse classCourseSave = new ClassCourse(
                new Name(classCourseModel.getName()),
                new Description(classCourse.getDescription()),
                new CryptoID(classCourse.getDisciplineID()),
                new CryptoID(classCourse.getTeacherId())
        );

        return classCourseSave;
    }
}
