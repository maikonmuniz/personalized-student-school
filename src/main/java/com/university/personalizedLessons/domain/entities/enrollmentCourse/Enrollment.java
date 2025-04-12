package com.university.personalizedLessons.domain.entities.enrollment;

import java.time.LocalDate;
import java.util.UUID;

public class Enrollment {

    private UUID id;
    private LocalDate dateCurrent;
    private UUID accountID;
    private UUID courseID;

    public Enrollment (
            String accountID,
            String courseID
    ) {
        this.id = UUID.randomUUID();
        this.dateCurrent = LocalDate.now();
        this.accountID = UUID.fromString(accountID);
        this.courseID = UUID.fromString(courseID);
    }

    public Enrollment (
            String id,
            LocalDate dateCurrent,
            String accountID,
            String courseID
    ) {
        this.id = UUID.fromString(id);
        this.dateCurrent = dateCurrent;
        this.accountID = UUID.fromString(accountID);
        this.courseID = UUID.fromString(courseID);
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDateCurrent() {
        return dateCurrent;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public UUID getCourseID() {
        return courseID;
    }
}
