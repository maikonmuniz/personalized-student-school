package com.university.personalizedLessons.domain.entities.classCourse;

import java.util.Date;
import java.util.UUID;

public class ClassCourse {

    private int id;
    private String name;
    private Date dataClass;
    private UUID courseID;
    private UUID disciplineID;
    private UUID teacherId;

    public ClassCourse (
            int id,
            String name,
            Date dataClass,
            UUID courseID,
            UUID disciplineID,
            UUID teacherId
    ) {
        this.id = id;
        this.name = name;
        this.dataClass = dataClass;
        this.courseID = courseID;
        this.disciplineID = disciplineID;
        this.teacherId = teacherId;
    }
}
