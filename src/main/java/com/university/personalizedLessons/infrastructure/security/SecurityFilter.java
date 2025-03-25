package com.university.personalizedLessons.infrastructure.security;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.valueObject.*;
import com.university.personalizedLessons.infrastructure.models.AccountModel;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenServices tokenService;
    @Autowired
    AccountJPA userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        var token = this.recoverToken(request);

        if(token != null){

            String login = this.tokenService.validateToken(token);
            AccountModel user = userRepository.findByUsername(login);

            try {

                Account account = new Account(
                        FirstName.create(user.getFirstName()),
                        LastName.create(user.getLastName()),
                        Cpf.create(user.getCpf()),
                        Username.create(user.getUsername()),
                        Password.create(user.getPassword()),
                        user.getTypeAccountModel().getId()
                );

                var authentication = new UsernamePasswordAuthenticationToken(login, account.getPassword(), null);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
