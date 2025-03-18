package com.university.personalizedLessons.infrastructure.dto;

public record RegisterAccountDTO (
        String firstName,
        String lastName,
        String cpf,
        String username,
        String password,
        int idTypeAccount
) { }
