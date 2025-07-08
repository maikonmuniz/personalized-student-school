package com.university.personalizedLessons.domain.entities.course;

import com.university.personalizedLessons.domain.valueObjectGlobal.*;

import java.util.UUID;

public class Course {

    private CryptoID courseID;
    private CourseID id;
    private Name name;
    private int typeCourseId;
    private Description description;
    private AccountID accountId;

    public Course(
            Name name,
            Description description,
            int typeCourseId,
            String accountId
    ){
        this.courseID = new CryptoID("");
        this.name = name;
        this.description = description;
        this.typeCourseId = typeCourseId;
        this.accountId = new AccountID(accountId);
    }

    public Course(
            UUID courseID,
            int id,
            Name name,
            Description description,
            int typeCourseId,
            String accountId
    ){
        this.courseID = new CryptoID(courseID.toString());
        this.id =  new CourseID(id);
        this.name = name;
        this.description = description;
        this.typeCourseId = typeCourseId;
        this.accountId = new AccountID(accountId);
    }

    public String getName() {
        return this.name.getValue();
    }

    public int getTypeCourseId() {
        return this.typeCourseId;
    }

    public String getDescription() {
        return description.getValue();
    }

    public int getId() {
        return id.getValue();
    }

    public String getAccountId() {
        return accountId.getValue();
    }

    public UUID getCourseID() {
        return courseID.getValue();
    }
}
