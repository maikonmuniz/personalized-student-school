package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "type_account")
@Table(name = "type_account")
public class TypeAccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type_register", nullable = false, unique = true, length = 255)
    private String typeRegister;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTypeRegister (String typeRegister) {
        this.typeRegister = typeRegister;
    }

    public String getTypeRegister () {
        return this.typeRegister;
    }

}
