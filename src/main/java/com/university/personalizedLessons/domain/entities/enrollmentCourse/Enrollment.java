package com.university.personalizedLessons.domain.entities.enrollmentCourse;

import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;

public class Enrollment {

    private CryptoID id;
    private CryptoID accountID;
    private CryptoID courseID;

    public Enrollment (
            String accountID,
            String courseID
    ) {
        this.id = new CryptoID("");
        this.accountID = new CryptoID(accountID);
        this.courseID = new CryptoID(courseID);
    }

    public Enrollment (
            String id,
            String accountID,
            String courseID
    ) {
        this.id = new CryptoID(id);
        this.accountID = new CryptoID(accountID);
        this.courseID = new CryptoID(courseID);
    }

    public String getId() {
        return id.getValue().toString();
    }

    public String getAccountID() {
        return accountID.getValue().toString();
    }

    public String getCourseID() {
        return  courseID.getValue().toString();
    }
}
