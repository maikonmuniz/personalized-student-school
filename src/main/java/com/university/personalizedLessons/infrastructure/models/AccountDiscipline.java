package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account_discipline")
public class AccountDiscipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 2, scale = 2)
    private BigDecimal notes;

    @ManyToOne
    @JoinColumn(name = "id_discipline", nullable = false)
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "id_account", nullable = false)
    private AccountModel account;

    @ManyToOne
    @JoinColumn(name = "id_type_note", nullable = false)
    private TypeNote typeNote;

}


