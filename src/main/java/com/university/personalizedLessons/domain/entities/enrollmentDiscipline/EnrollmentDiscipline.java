package com.university.personalizedLessons.domain.entities.enrollmentDiscipline;

import java.util.UUID;

public class Enrollment {

    private UUID id;
    private UUID accountID;
    private UUID disciplineID;

    public Enrollment (
            String id,
            String accountID,
            String disciplineID
    ) {
        this.id = UUID.fromString(id);
        this.accountID = UUID.fromString(accountID);
        this.disciplineID = UUID.fromString(disciplineID);
    }

    public Enrollment (
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

    public UUID getAccountID() {
        return accountID;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
    }

    public UUID getDisciplineID() {
        return disciplineID;
    }

    public void setDisciplineID(UUID disciplineID) {
        this.disciplineID = disciplineID;
    }
}
