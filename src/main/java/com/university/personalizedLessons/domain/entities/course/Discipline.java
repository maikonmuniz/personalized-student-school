package com.university.personalizedLessons.domain.entities.course;

import java.util.UUID;

public class Discipline {

    private UUID id;
    private String name;
    private String description;

    public Discipline (
            UUID id,
            String name,
            String description
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
