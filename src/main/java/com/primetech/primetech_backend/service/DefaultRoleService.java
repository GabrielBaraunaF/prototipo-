package com.primetech.primetech_backend.service;

import com.primetech.primetech_backend.entity.Role;
import com.primetech.primetech_backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultRoleService implements RoleService {

    private static final Integer ROLE_COLABORATOR = 3;

    private static final Integer ROLE_USER = 1;

    @Autowired
    private RoleRepository repository;


    @Override
    public Role register() {
      return repository.findById(ROLE_USER).get();
    }

    @Override
    public Role updateRole() {
       return repository.findById(ROLE_COLABORATOR).get();
    }
}
