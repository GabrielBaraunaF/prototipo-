package com.primetech.primetech_backend.facade;

import com.primetech.primetech_backend.dto.LoginDto;
import com.primetech.primetech_backend.dto.UserCreateDTO;
import com.primetech.primetech_backend.dto.UserResponseDTO;
import com.primetech.primetech_backend.entity.User;

import java.util.List;

public interface UserFacade {
    UserResponseDTO save(UserCreateDTO user);
    List<UserResponseDTO> findAll();
    User authenticate(LoginDto loginDto);

    void updateUser(String email);
}
