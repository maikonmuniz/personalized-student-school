package com.university.personalizedLessons.infrastructure.operationORM;

import com.university.personalizedLessons.infrastructure.models.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJPA extends JpaRepository<TestModel, Long> {



}
