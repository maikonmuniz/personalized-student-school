package com.university.personalizedLessons.domain.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    public void generateAccount() {
        Account account = new Account("maikon", "muniz", "34242342344234");
        assertEquals("maikon", account.getFirstName());
        assertEquals("muniz", account.getLastName());
        assertEquals("34242342344234", account.getCpf());
    }
}