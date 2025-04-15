package com.university.personalizedLessons.dto;

public record CreateClassDTO (
        String name,
        String teacherID,
        String disciplineID,
        String description
) { }
