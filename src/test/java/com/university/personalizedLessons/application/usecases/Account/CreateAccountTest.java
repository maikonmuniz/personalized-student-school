package com.university.personalizedLessons.application.usecases.Account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateAccountTest {

    @InjectMocks
    private CreateAccount createAccount;

    String firstName = "Maikon";
    String lastName = "Muniz";
    String cpf = "435242234234";

    @Test
    public void createAccount () {

        CreateAccount.Input input = new CreateAccount.Input(
                "Maikon",
                "Muniz",
                "435242234234"
        );

        CreateAccount.Output output = createAccount.execute(input);

        assertEquals(firstName, output.firstName());
        assertEquals(lastName, output.lastName());
        assertEquals(cpf, output.cpf());

    }
}