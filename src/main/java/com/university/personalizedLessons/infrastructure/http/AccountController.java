package com.university.personalizedLessons.infrastructure.http;

import com.university.personalizedLessons.application.usecases.account.CreateAccount;
import com.university.personalizedLessons.application.usecases.account.LoginAccount;
import com.university.personalizedLessons.infrastructure.dto.LoginDTO;
import com.university.personalizedLessons.infrastructure.dto.RegisterAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    CreateAccount createAccount;

    @Autowired
    LoginAccount loginAccount;

    @PostMapping("register")
    public ResponseEntity registerAccount (@RequestBody RegisterAccountDTO data) {

        CreateAccount.Output output = this.createAccount.execute(
                new CreateAccount.Input(
                        data.firstName(),
                        data.lastName(),
                        data.cpf(),
                        data.username(),
                        data.password(),
                        data.idTypeAccount()
                )
        );

        return ResponseEntity.ok(output);

    }

    @PostMapping("login")
    public ResponseEntity loginAccount (@RequestBody LoginDTO data) {

        LoginAccount.Output output = this.loginAccount.execute(
                new LoginAccount.Input(
                        data.username(),
                        data.password()
                )
        );

        return ResponseEntity.ok(output);
    }
}
