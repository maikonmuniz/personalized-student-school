package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
@Entity(name = "account")
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_account", length = 36, unique = true, columnDefinition = "VARCHAR(36)")
    private String idAccount;

    @Column(name = "username", length = 100, unique = true)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "id_type_account")
    private TypeAccountModel typeAccountModel;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getIdAccount() {
        return this.idAccount;
    }

    public void setTypeAccountModel (TypeAccountModel typeAccountModel) {
        this.typeAccountModel = typeAccountModel;
    }

    public TypeAccountModel getTypeAccountModel() {
        return this.typeAccountModel;
    }
}
