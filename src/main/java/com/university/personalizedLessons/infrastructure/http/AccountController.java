package com.university.personalizedLessons.infrastructure.http;

import com.university.personalizedLessons.application.usecases.Account.CreateAccount;
import com.university.personalizedLessons.infrastructure.dto.RegisterAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    CreateAccount createAccount;

    @PostMapping("register")
    public ResponseEntity registerAccount (@RequestBody RegisterAccountDTO data) {
        CreateAccount.Output output = this.createAccount.execute(
                new CreateAccount.Input(
                        data.firstName(),
                        data.lastName(),
                        data.cpf(),
                        data.username(),
                        data.password()
                )
        );

        return ResponseEntity.ok(output);

    }
}
