package com.university.personalizedLessons.domain.entities.course;

public class CourseAggregate {

    private final int id;
    private final String name;
    private final int typeCourseId;
    private final String description;

    public CourseAggregate(
            int id,
            String name,
            String description,
            int typeCourseId
    ){
        this.id = id;
        this.name = name;
        this.description = description;
        this.typeCourseId = typeCourseId;
    }

    public String getName() {
        return this.name;
    }

    public int getTypeCourseId() {
        return this.typeCourseId;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}
