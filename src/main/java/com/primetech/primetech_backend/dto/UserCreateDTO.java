package com.primetech.primetech_backend.dto;

import lombok.Data;

@Data
public class UserCreateDTO {

    private String Username;

    private String email;

    private String Password;

    private String cpf;

    private String school;
}
