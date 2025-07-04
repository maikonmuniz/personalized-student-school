package com.university.personalizedLessons.dto;

public record CreateDisciplineDTO (
        String name,
        String description,
        String accountID,
        int courseID
) { }
