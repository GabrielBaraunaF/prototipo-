package com.primetech.primetech_backend.service;

import com.primetech.primetech_backend.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    Role register();

    Role updateRole();
}
