package com.university.personalizedLessons.domain.entities.discipline;

import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;

import java.util.UUID;

public class DisciplineAggregate {

    private int id;
    private String disciplineID;
    private Name name;
    private Description description;
    private int courseID;

    public DisciplineAggregate (
            Name name,
            Description description,
            int courseID
    ) {
        this.disciplineID = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.courseID = courseID;
    }

    public DisciplineAggregate (
            int id,
            String disciplineID,
            Name name,
            Description description,
            int courseID
    ) {
        this.id = id;
        this.disciplineID = disciplineID;
        this.name = name;
        this.description = description;
        this.courseID = courseID;
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
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getDisciplineID() {
        return disciplineID;
    }
}
