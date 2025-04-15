package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.ClassCourseRepository;
import com.university.personalizedLessons.domain.entities.classCourse.ClassCourse;
import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.IsActive;
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

        classCourseModel.setClassID(classCourse.getId());
        classCourseModel.setAccountModel(accountModel);
        classCourseModel.setName(nameValue);
        classCourseModel.setDescription(descriptionValue);
        classCourseModel.setLocalDateTimeInit(dateValue);
        classCourseModel.setActiveDiscipline(classCourse.isActive());

        classCourseModel = this.classCourseJPA.save(classCourseModel);

        CryptoID id = new CryptoID(classCourseModel.getClassID());
        Name name = new Name(classCourse.getName());
        Description description = new Description(classCourse.getDescription());
        CryptoID teacherID = new CryptoID(classCourse.getTeacherId().toString());
        CryptoID disciplineID = new CryptoID(classCourse.getDisciplineID());
        LocalDateTime date = classCourseModel.getLocalDateTimeInit();
        IsActive isActive = new IsActive();

        return new ClassCourse(
                id,
                name,
                description,
                date,
                teacherID,
                disciplineID,
                isActive
        );
    }
}
