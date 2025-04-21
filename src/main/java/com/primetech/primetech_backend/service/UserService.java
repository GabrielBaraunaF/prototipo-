package com.primetech.primetech_backend.service;

import com.primetech.primetech_backend.dto.LoginDto;
import com.primetech.primetech_backend.dto.UserCreateDTO;
import com.primetech.primetech_backend.dto.UserResponseDTO;
import com.primetech.primetech_backend.entity.Role;
import com.primetech.primetech_backend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService{
    User save(UserCreateDTO user, Role role);
    List<UserResponseDTO> findAll();
    User authenticate(LoginDto loginDto);

    User findUserByEmail(String email);

    void updateUser(String email, Role role);
}

