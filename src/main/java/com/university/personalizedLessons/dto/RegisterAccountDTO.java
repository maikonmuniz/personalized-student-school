package com.university.personalizedLessons.dto;

public record RegisterAccountDTO (
        String firstName,
        String lastName,
        String cpf,
        String username,
        String password,
        int idTypeAccount
) { }
