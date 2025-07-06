package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.test.TestClass;

public interface TaskRepository {
    TestClass save(TestClass test);
}
