package com.university.personalizedLessons.dto;

public record CreateCourseDTO(
        String name,
        String description,
        int type_course_id,
        String username
) { }