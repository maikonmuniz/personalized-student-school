package com.university.personalizedLessons.domain.entities.discipline;

import java.util.UUID;

public class DisciplineAggregate {

    private UUID id;
    private String name;
    private String description;
    private UUID courseID;

    public DisciplineAggregate(
            UUID id,
            String name,
            String description,
            UUID courseID
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.courseID = courseID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCourseID() {
        return courseID;
    }

    public void setCourseID(UUID courseID) {
        this.courseID = courseID;
    }
}
