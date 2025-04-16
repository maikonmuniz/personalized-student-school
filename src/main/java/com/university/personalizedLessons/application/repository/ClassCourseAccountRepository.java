package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.classCourse.ClassPresence;

public interface ClassCourseAccountRepository {
    ClassPresence save (ClassPresence classPresence);
}
