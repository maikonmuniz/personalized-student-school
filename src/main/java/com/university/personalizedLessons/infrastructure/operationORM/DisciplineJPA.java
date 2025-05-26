package com.university.personalizedLessons.infrastructure.operationORM;

import com.university.personalizedLessons.infrastructure.models.DisciplineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisciplineJPA extends JpaRepository<DisciplineModel, Integer > {
    @Query(value = "SELECT * FROM discipline d where d.id_course = :courseId;", nativeQuery = true)
    List<DisciplineModel> findAll (@Param("courseId") int courseId);

    @Query(value = "SELECT * FROM discipline d where d.discipline_id = :id;", nativeQuery = true)
    DisciplineModel consultDiscipline (@Param("id") String id);
}
