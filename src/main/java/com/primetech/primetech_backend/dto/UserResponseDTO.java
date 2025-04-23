package com.primetech.primetech_backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private String Username;

    private String email;

    private String school;

    private List<String> roles;

}
