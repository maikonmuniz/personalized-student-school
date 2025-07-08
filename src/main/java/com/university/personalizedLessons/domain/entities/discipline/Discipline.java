package com.university.personalizedLessons.domain.entities.discipline;

import com.university.personalizedLessons.domain.valueObjectGlobal.CourseID;
import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;

import java.util.UUID;

public class Discipline {

    private int id;
    private String disciplineID;
    private Name name;
    private Description description;
    private CryptoID accountID;
    private CourseID courseID;

    public Discipline(
            Name name,
            Description description,
            String accountID,
            int courseID
    ) {
        this.disciplineID = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.accountID = new CryptoID(accountID);
        this.courseID = new CourseID(courseID);
    }

    public Discipline(
            int id,
            String disciplineID,
            Name name,
            Description description,
            String accountID,
            int courseID
    ) {
        this.id = id;
        this.disciplineID = disciplineID;
        this.name = name;
        this.description = description;
        this.accountID = new CryptoID(accountID);
        this.courseID = new CourseID(courseID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getDescription() {
        return description.getValue();
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public int getCourseID() {
        return courseID.getValue();
    }

    public void setCourseID(int courseID) {
        this.courseID = new CourseID(courseID);
    }

    public String getDisciplineID() {
        return disciplineID;
    }

    public String getAccountID() {
        return accountID.getValue().toString();
    }

    public void setAccountID(String accountID) {
        this.accountID = new CryptoID(accountID);
    }
}
