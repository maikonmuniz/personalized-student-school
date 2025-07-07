package com.university.personalizedLessons.domain.entities.enrollmentDiscipline;

import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;

import java.util.UUID;

public class EnrollmentDiscipline {

    private CryptoID id;
    private CryptoID accountID;
    private CryptoID disciplineID;

    public EnrollmentDiscipline(
            String id,
            String accountID,
            String disciplineID
    ) {
        this.id = new CryptoID(id);
        this.accountID = new CryptoID(accountID);
        this.disciplineID = new CryptoID(disciplineID);
    }

    public EnrollmentDiscipline(
            String accountID,
            String disciplineID
    ) {
        this.id = new CryptoID("");
        this.accountID = new CryptoID(accountID);
        this.disciplineID = new CryptoID(disciplineID);
    }

    public String getId() {
        return id.getValue().toString();
    }

    public void setId(String id) {
        this.id = new CryptoID(id);
    }

    public String getAccountID() {
        return accountID.getValue().toString();
    }

    public void setAccountID(String accountID) {
        this.accountID = new CryptoID(accountID);
    }

    public String getDisciplineID() {
        return disciplineID.toString();
    }

    public void setDisciplineID(String disciplineID) {
        this.disciplineID = new CryptoID(disciplineID);
    }
}
