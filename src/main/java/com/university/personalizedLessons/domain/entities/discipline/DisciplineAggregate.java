package com.university.personalizedLessons.domain.entities.discipline;

import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;

public class DisciplineAggregate {

    private int id;
    private Name name;
    private Description description;
    private int courseID;

    public DisciplineAggregate (
            int id,
            Name name,
            Description description,
            int courseID
    ) {
        this.id = id;
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
}
