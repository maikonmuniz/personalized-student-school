package com.university.personalizedLessons.domain.entities.enrollmentDiscipline;

import java.util.UUID;

public class EnrollmentDiscipline {

    private UUID id;
    private UUID accountID;
    private UUID disciplineID;

    public EnrollmentDiscipline(
            String id,
            String accountID,
            String disciplineID
    ) {
        this.id = UUID.fromString(id);
        this.accountID = UUID.fromString(accountID);
        this.disciplineID = UUID.fromString(disciplineID);
    }

    public EnrollmentDiscipline(
            String accountID,
            String disciplineID
    ) {
        this.id = UUID.randomUUID();
        this.accountID = UUID.fromString(accountID);
        this.disciplineID = UUID.fromString(disciplineID);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccountID() {
        return accountID.toString();
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }

    public String getDisciplineID() {
        return disciplineID.toString();
    }

    public void setDisciplineID(String disciplineID) {
        this.disciplineID = UUID.fromString(disciplineID);
    }
}
