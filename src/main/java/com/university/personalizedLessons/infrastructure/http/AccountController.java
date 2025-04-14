package com.university.personalizedLessons.infrastructure.http;

import com.university.personalizedLessons.application.usecases.account.AccountAcceptDiscipline;
import com.university.personalizedLessons.application.usecases.account.CreateAccount;
import com.university.personalizedLessons.application.usecases.account.LoginAccount;
import com.university.personalizedLessons.application.usecases.course.RegisterAccountInCourse;
import com.university.personalizedLessons.dto.AcceptDisciplineDTO;
import com.university.personalizedLessons.dto.AccountCourseRegisterDTO;
import com.university.personalizedLessons.dto.LoginDTO;
import com.university.personalizedLessons.dto.RegisterAccountDTO;
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

    @Autowired
    RegisterAccountInCourse registerAccountInCourse;

    @Autowired
    AccountAcceptDiscipline accountAcceptDiscipline;

    @PostMapping("register")
    public ResponseEntity registerAccount (@RequestBody RegisterAccountDTO data) throws Exception {

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

    @PostMapping("register-account-course")
    public ResponseEntity accountCourse (@RequestBody AccountCourseRegisterDTO data) {

        RegisterAccountInCourse.Output output = this.registerAccountInCourse.execute(new RegisterAccountInCourse.Input(
                data.accountID(),
                data.courseID()
        ));

        return ResponseEntity.ok(output);
    }

    @PostMapping("register-accept-discipline")
    public ResponseEntity accountDiscipline (@RequestBody AcceptDisciplineDTO data) {

        AccountAcceptDiscipline.Input input = new AccountAcceptDiscipline.Input (
                data.accountID(),
                data.disciplineID()
        );

        AccountAcceptDiscipline.Output output = this.accountAcceptDiscipline.execute(input);

        return ResponseEntity.ok(output);

    }
}
