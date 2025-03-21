package com.curso.domains.dtos;

import com.curso.domains.User;
import com.curso.domains.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {

    protected Long id;

    @NotNull(message = "O campo nome não pode ser nulo!")
    @NotBlank(message = "O campo nome não pode ser vazio!")
    protected String firstName;

    @NotNull(message = "O campo sobrenome não pode ser nulo!")
    @NotBlank(message = "O campo sobrenome não pode ser vazio!")
    protected String lastName;

    @NotNull(message = "O campo CPF não pode ser nulo!")
    @CPF
    protected String cpf;

    @NotNull(message = "O campo e-mail não pode ser nulo!")
    @NotBlank(message = "O campo e-mail não pode ser vazio")
    protected String email;

    @NotNull(message = "O campo senha não pode ser nulo!")
    @NotBlank(message = "O campo senha não pode ser vazio")
    protected String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate createdAt = LocalDate.now();

    protected Set<Integer> personType = new HashSet<>();

    public UserDTO(){

    }

    public UserDTO(User obj) {
        this.id = obj.getId();
        this.firstName = obj.getFirstName();
        this.lastName = obj.getLastName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.createdAt = obj.getCreatedAt();
        this.personType.stream().map(PersonType :: toEnum).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O campo nome não pode ser nulo!") @NotBlank(message = "O campo nome não pode ser vazio!") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "O campo nome não pode ser nulo!") @NotBlank(message = "O campo nome não pode ser vazio!") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "O campo sobrenome não pode ser nulo!") @NotBlank(message = "O campo sobrenome não pode ser vazio!") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = "O campo sobrenome não pode ser nulo!") @NotBlank(message = "O campo sobrenome não pode ser vazio!") String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = "O campo CPF não pode ser nulo!") @CPF String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull(message = "O campo CPF não pode ser nulo!") @CPF String cpf) {
        this.cpf = cpf;
    }

    public @NotNull(message = "O campo e-mail não pode ser nulo!") @NotBlank(message = "O campo e-mail não pode ser vazio") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "O campo e-mail não pode ser nulo!") @NotBlank(message = "O campo e-mail não pode ser vazio") String email) {
        this.email = email;
    }

    public @NotNull(message = "O campo senha não pode ser nulo!") @NotBlank(message = "O campo senha não pode ser vazio") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "O campo senha não pode ser nulo!") @NotBlank(message = "O campo senha não pode ser vazio") String password) {
        this.password = password;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Set<PersonType> getPersonType() {
        return personType == null ? Collections.emptySet() :
                personType.stream().map(PersonType :: toEnum).collect(Collectors.toSet());
    }

    public void addPersonType(PersonType personType) {
        this.personType.add(personType.getId());
    }
}

