package com.university.personalizedLessons.infrastructure.operationORM;

import com.university.personalizedLessons.infrastructure.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJPA extends JpaRepository <AccountModel, Long> {
    AccountModel findByUsername(String username);
}
