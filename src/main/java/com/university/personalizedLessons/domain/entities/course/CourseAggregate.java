package com.university.personalizedLessons.domain.entities.course;

import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;

public class CourseAggregate {

    private int id;
    private Name name;
    private int typeCourseId;
    private Description description;
    private String usernameID;

    public CourseAggregate(
            Name name,
            Description description,
            int typeCourseId,
            String usernameID
    ){
        this.name = name;
        this.description = description;
        this.typeCourseId = typeCourseId;
        this.usernameID = usernameID;
    }

    public CourseAggregate(
            int id,
            Name name,
            Description description,
            int typeCourseId,
            String usernameID
    ){
        this.id = id;
        this.name = name;
        this.description = description;
        this.typeCourseId = typeCourseId;
        this.usernameID = usernameID;
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

    public String getUsernameID() {
        return usernameID;
    }
}
