package com.university.personalizedLessons.infrastructure.operationORM;

import com.university.personalizedLessons.infrastructure.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountJPA extends JpaRepository <AccountModel, Long> {
    AccountModel findByUsername(String username);

    @Query(value = "SELECT * FROM account c where c.id_account = :id;", nativeQuery = true)
    AccountModel consultaBrasil(@Param("id") String id);
}
