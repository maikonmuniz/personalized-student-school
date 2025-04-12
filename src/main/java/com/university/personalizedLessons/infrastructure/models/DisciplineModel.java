package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discipline")
public class DisciplineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "discipline_id", length = 36, unique = true)
    private String disciplineID;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 250)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private CourseModel course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }

    public String getDisciplineID() {
        return disciplineID;
    }

    public void setDisciplineID(String disciplineID) {
        this.disciplineID = disciplineID;
    }
}

