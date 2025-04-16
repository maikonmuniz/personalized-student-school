package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.ClassCourseAccountRepository;
import com.university.personalizedLessons.domain.entities.classCourse.ClassPresence;
import com.university.personalizedLessons.domain.entities.classCourse.vo.Presence;
import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;
import com.university.personalizedLessons.infrastructure.models.AccountModel;
import com.university.personalizedLessons.infrastructure.models.ClassCourseAccountModel;
import com.university.personalizedLessons.infrastructure.models.ClassCourseModel;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.ClassCourseAccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.ClassCourseJPA;

public class ClassCourseAccountRepo implements ClassCourseAccountRepository {

    private ClassCourseAccountJPA classAccountJPA;
    private ClassCourseJPA classCourseJPA;
    private AccountJPA accountJPA;

    public ClassCourseAccountRepo (
            ClassCourseAccountJPA classAccountJPA,
            ClassCourseJPA classCourseJPA,
            AccountJPA accountJPA
    ) {
        this.classAccountJPA = classAccountJPA;
        this.classCourseJPA = classCourseJPA;
        this.accountJPA = accountJPA;
    }

    @Override
    public ClassPresence save(ClassPresence classPresence) {

        String classIDConsult = classPresence.getClassId();

        ClassCourseModel classTeacher = this.classCourseJPA.consultClassId(classIDConsult);
        AccountModel accountModel = this.accountJPA.consultAccountId(classPresence.getStudentID().toString());

        ClassCourseAccountModel presenceClass = new ClassCourseAccountModel();
        presenceClass.setClassCourse(classTeacher);
        presenceClass.setPresenceId(classPresence.getId().getValue().toString());
        presenceClass.setPresence(true);
        presenceClass.setAccountModel(accountModel);

        presenceClass = this.classAccountJPA.save(presenceClass);

        CryptoID presenceID = new CryptoID(presenceClass.getPresenceId());
        CryptoID classID = new CryptoID(presenceClass.getClassCourse().getClassID().toString());
        CryptoID studantID = new CryptoID(presenceClass.getAccountModel().getIdAccount());
        Presence presence = new Presence(true);

        ClassPresence classPresenceSave = new ClassPresence(
                presenceID,
                classID,
                studantID,
                presence
        );

        return classPresenceSave;
    }
}
