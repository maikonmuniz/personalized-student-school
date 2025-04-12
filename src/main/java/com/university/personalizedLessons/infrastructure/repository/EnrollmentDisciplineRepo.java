package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.AccountDisciplineRepository;
import com.university.personalizedLessons.domain.entities.enrollmentDiscipline.EnrollmentDiscipline;
import com.university.personalizedLessons.infrastructure.models.AccountDisciplineModel;
import com.university.personalizedLessons.infrastructure.models.AccountModel;
import com.university.personalizedLessons.infrastructure.models.DisciplineModel;
import com.university.personalizedLessons.infrastructure.operationORM.AccountDisciplineJpa;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.DisciplineJPA;

public class EnrollmentDisciplineRepo implements AccountDisciplineRepository {

    private AccountJPA accountJPA;
    private DisciplineJPA disciplineJPA;
    private AccountDisciplineJpa accountDisciplineJpa;

    public EnrollmentDisciplineRepo (
            AccountJPA accountJPA,
            DisciplineJPA disciplineJPA,
            AccountDisciplineJpa accountDisciplineJpa
    ) {
        this.accountJPA = accountJPA;
        this.disciplineJPA = disciplineJPA;
        this.accountDisciplineJpa = accountDisciplineJpa;
    }

    @Override
    public EnrollmentDiscipline save(
            EnrollmentDiscipline enrollmentDiscipline
    ) {

        String accountID = enrollmentDiscipline.getAccountID().toString();
        AccountModel accountModel = this.accountJPA.consultAccountId(accountID);

        String disciplineID = enrollmentDiscipline.getDisciplineID().toString();
        DisciplineModel disciplineModel = this.disciplineJPA.consultDiscipline(disciplineID);

        AccountDisciplineModel accountDisciplineModel = new AccountDisciplineModel();

        String id = enrollmentDiscipline.getId().toString();

        accountDisciplineModel.setAccountDisciplineId(id);
        accountDisciplineModel.setAccountModel(accountModel);
        accountDisciplineModel.setDiscipline(disciplineModel);

        accountDisciplineModel = this.accountDisciplineJpa.save(accountDisciplineModel);

        EnrollmentDiscipline enrollmentDisciplineSave = new EnrollmentDiscipline(
                accountDisciplineModel.getAccountDisciplineId(),
                accountDisciplineModel.getAccountModel().getIdAccount(),
                accountDisciplineModel.getDiscipline().getDisciplineID()
        );

        return enrollmentDisciplineSave;
    }
}
