package com.university.personalizedLessons.main.springSecurity;

import com.university.personalizedLessons.infrastructure.security.TokenAdapter;
import com.university.personalizedLessons.infrastructure.security.TokenServices;
import com.university.personalizedLessons.infrastructure.springSecurityBcripty.CryptAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainSpringSecurity {

    @Bean
    CryptAdapter createBcrypt () {
        return new CryptAdapter();
    }

    @Bean
    TokenAdapter createTokenJWT (
            TokenServices jwt
    ) {
        return new TokenAdapter(
                jwt
        );
    }
}
