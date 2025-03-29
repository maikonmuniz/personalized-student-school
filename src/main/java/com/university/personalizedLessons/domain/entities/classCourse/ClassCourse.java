package com.university.personalizedLessons.domain.entities.classCourse;

import java.util.UUID;

public class ClassCourse {

    private UUID id;
    private String name;
    private String description;
    private boolean presence;

    public ClassCourse (
            String description,
            boolean presence,
            String name
    ) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.presence = presence;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPresence() {
        return presence;
    }
}
