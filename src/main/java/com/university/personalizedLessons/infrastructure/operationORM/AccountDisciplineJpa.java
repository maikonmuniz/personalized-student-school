package com.university.personalizedLessons.infrastructure.operationORM;

import com.university.personalizedLessons.infrastructure.models.AccountDisciplineModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDisciplineJpa extends JpaRepository<AccountDisciplineModel, Long> { }
