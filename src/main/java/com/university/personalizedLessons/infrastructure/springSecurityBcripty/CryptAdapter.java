package com.university.personalizedLessons.infrastructure.springSecurityBcripty;

import com.university.personalizedLessons.interfaceAdapters.InterCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptAdapter implements InterCrypt {

    @Override
    public String encrypt(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public boolean verifyPassword (String password, String passwordCrypt) {
        return new BCryptPasswordEncoder().matches(password, passwordCrypt);
    }
}
