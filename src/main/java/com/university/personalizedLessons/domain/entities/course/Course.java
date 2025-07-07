package com.university.personalizedLessons.domain.entities.course;

import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;

import java.util.UUID;

public class Course {

    private UUID courseID;
    private int id;
    private Name name;
    private int typeCourseId;
    private Description description;
    private String accountId;

    public Course(
            Name name,
            Description description,
            int typeCourseId,
            String accountId
    ){
        this.courseID = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.typeCourseId = typeCourseId;
        this.accountId = accountId;
    }

    public Course(
            UUID courseID,
            int id,
            Name name,
            Description description,
            int typeCourseId,
            String accountId
    ){
        this.courseID = courseID;
        this.id = id;
        this.name = name;
        this.description = description;
        this.typeCourseId = typeCourseId;
        this.accountId = accountId;
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
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public UUID getCourseID() {
        return courseID;
    }
}
