package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "test")
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_type_note")
    private TypeNote typeNote;

    @ManyToOne
    @JoinColumn(name = "id_class_course_account")
    private ClassCourseAccount classCourseAccount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TypeNote getTypeNote() {
        return typeNote;
    }

    public void setTypeNote(TypeNote typeNote) {
        this.typeNote = typeNote;
    }

    public ClassCourseAccount getClassCourseAccount() {
        return classCourseAccount;
    }

    public void setClassCourseAccount(ClassCourseAccount classCourseAccount) {
        this.classCourseAccount = classCourseAccount;
    }
}
