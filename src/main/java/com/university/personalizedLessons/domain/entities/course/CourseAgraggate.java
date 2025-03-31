package com.university.personalizedLessons.domain.entities.course;

import java.util.ArrayList;
import java.util.List;

public class CourseAgraggate {

    private int id;
    private final String name;
    private final int typeCourseId;
    private final String description;
    private List<Discipline> listDiscipline;

    public CourseAgraggate(
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

    public List<Discipline> getListDiscipline() {
        return listDiscipline;
    }

    public void setListDiscipline(List<Discipline> listDiscipline) {
        this.listDiscipline = listDiscipline;
    }
}
