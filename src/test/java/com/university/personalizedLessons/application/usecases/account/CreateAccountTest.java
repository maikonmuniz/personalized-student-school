package com.university.personalizedLessons.application.usecases.account;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.valueObject.account.*;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.models.AccountModel;
import com.university.personalizedLessons.infrastructure.models.TypeAccountModel;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.springSecurityBcripty.CryptAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CreateAccountTest {

    @InjectMocks
    private CreateAccount createAccount;

    @Mock
    private AccountRepo accountRepo;

    @Mock
    private CryptAdapter crypt;

    @Mock
    private ExceptionAdapter exceptionAdapter;

    @Test
    @DisplayName("Should save entity in data base!")
    public void createAccount () throws Exception {

        AccountJPA accountJPAMock = mock(AccountJPA.class);

        AccountRepo accountRepo = new AccountRepo(accountJPAMock);

        String passwordValue = "$2a$10$E.Mzzqz7KjShGp9nB3Fq6uL2P8I7V1l.U9cjjWfPkp3bP0Drx2x1W";

        Password password = Password.create(passwordValue);

        FirstName firstName = FirstName.create("maikon");
        LastName lastName = LastName.create("muniz");
        Cpf cpf = Cpf.create("3618829515");
        Username username = Username.create("maikon123@");
        int idTypeAccount = 1;

        Account account = new Account(
                firstName,
                lastName,
                cpf,
                username,
                password,
                idTypeAccount
        );

        AccountModel savedAccountModel = new AccountModel();
        savedAccountModel.setFirstName(firstName.getValue());
        savedAccountModel.setLastName(lastName.getValue());
        savedAccountModel.setCpf(cpf.getValue());
        savedAccountModel.setUsername(username.getValue());
        savedAccountModel.setPassword(password.getValue());

        TypeAccountModel typeAccountModel = new TypeAccountModel();
        typeAccountModel.setId(1);
        savedAccountModel.setTypeAccountModel(typeAccountModel);

        when(accountJPAMock.save(any(AccountModel.class))).thenReturn(savedAccountModel);

        Account savedAccount = accountRepo.save(account);

        assertNotNull(savedAccount);
        assertEquals(firstName.getValue(), savedAccount.getFirstName());
        assertEquals(lastName.getValue(), savedAccount.getLastName());
        assertEquals(cpf.getValue(), savedAccount.getCpf());
        assertEquals(username.getValue(), savedAccount.getUsername());
        assertEquals(password.getValue(), savedAccount.getPassword());

    }

    @Test
    @DisplayName("Verification if return exeption cpf invalid!")
    public void createAccountValidationFieldCPF () throws Exception {

        String firstName = "maikon";
        String lastName = "teste";
        String cpfErr = "3618829515";
        String username = "maikon123";
        String password = "maikon12ee3";
        int idTypeAccount = 2;

        when(this.exceptionAdapter.badRequest("CPF | invalid!"))
                .thenThrow(new RuntimeException("CPF | invalid!"));

        assertThrows(RuntimeException.class, () -> this.createAccount.execute(
                new CreateAccount.Input(
                        firstName,
                        lastName,
                        cpfErr,
                        username,
                        password,
                        idTypeAccount
                )));

        verify(this.exceptionAdapter, times(1)).badRequest("CPF | invalid!");

    }

    @Test
    @DisplayName("Verification if return exception the field username invalid!")
    public void createAccountValidationUserName () throws Exception {

        String firstName = "maikon";
        String lastName = "teste";
        String cpf = "36188295157";
        String usernameErr = "";
        String password = "maikon12ee3";
        int idTypeAccount = 2;

        when(this.exceptionAdapter.badRequest("Username | field is invalid!"))
                .thenThrow(new RuntimeException("Username | field is invalid!"));

        assertThrows(RuntimeException.class, () -> this.createAccount.execute(
                new CreateAccount.Input(
                        firstName,
                        lastName,
                        cpf,
                        usernameErr,
                        password,
                        idTypeAccount
                )));

        verify(this.exceptionAdapter, times(1)).badRequest("Username | field is invalid!");

    }

    @Test
    @DisplayName("Verification if return exception field password invalid!")
    public void createAccountValidationFieldPassword () throws Exception {

        String firstName = "maikon";
        String lastName = "teste";
        String cpf = "36188295157";
        String username= "maikonmuniz@";
        String passwordErr = "maikon";
        int idTypeAccount = 2;

        String message = "Password | invalid!";

        when(this.exceptionAdapter.badRequest(message))
                .thenThrow(new RuntimeException(message));

        assertThrows(RuntimeException.class, () -> this.createAccount.execute(
                new CreateAccount.Input(
                        firstName,
                        lastName,
                        cpf,
                        username,
                        passwordErr,
                        idTypeAccount
                )));

        verify(this.exceptionAdapter, times(1)).badRequest(message);

    }

    @Test
    @DisplayName("Verification if return exception field first name invalid!")
    public void createAccountValidationFieldFirstName () throws Exception {

        String firstNameErr = "";
        String lastName = "teste";
        String cpf = "36188295157";
        String username= "maikonmuniz@";
        String password = "maikonMuniz@5543";
        int idTypeAccount = 2;

        String message = "First name | invalid!";

        when(this.exceptionAdapter.badRequest(message))
                .thenThrow(new RuntimeException(message));

        assertThrows(RuntimeException.class, () -> this.createAccount.execute(
                new CreateAccount.Input(
                        firstNameErr,
                        lastName,
                        cpf,
                        username,
                        password,
                        idTypeAccount
                )));

        verify(this.exceptionAdapter, times(1)).badRequest(message);

    }

    @Test
    @DisplayName("Verification if return exception field last name invalid!")
    public void createAccountValidationFieldLastName () throws Exception {

        String firstNameErr = "maikon";
        String lastNameErr = "";
        String cpf = "36188295157";
        String username= "maikonmuniz@";
        String password = "maikonMuniz@5543";
        int idTypeAccount = 2;

        String message = "Last name ! invalid!";

        when(this.exceptionAdapter.badRequest(message))
                .thenThrow(new RuntimeException(message));

        assertThrows(RuntimeException.class, () -> this.createAccount.execute(
                new CreateAccount.Input(
                        firstNameErr,
                        lastNameErr,
                        cpf,
                        username,
                        password,
                        idTypeAccount
                )));

        verify(this.exceptionAdapter, times(1)).badRequest(message);

    }
}
