package com.primetech.primetech_backend.service;

import com.primetech.primetech_backend.dto.LoginDto;
import com.primetech.primetech_backend.dto.UserCreateDTO;
import com.primetech.primetech_backend.dto.UserResponseDTO;
import com.primetech.primetech_backend.entity.Role;
import com.primetech.primetech_backend.entity.User;
import com.primetech.primetech_backend.exception.ApplicationException;
import com.primetech.primetech_backend.repository.UserRepository;
import com.primetech.primetech_backend.util.Codificar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserCreateDTO userCreateDTO,Role role) {

        validateInsert(userCreateDTO.getCpf(),userCreateDTO.getEmail());

        String prefixPassword = "w.";

        User user =convertToUser(userCreateDTO);
        user.setPassword(Codificar.generateHash(prefixPassword+user.getCpf()));
        user.setRoles(new ArrayList<>());
        user.getRoles().add(role);
         return userRepository.save(user);
    }


    @Override
    public List<UserResponseDTO> findAll() {
        List<User> lista= userRepository.findAll();
        lista.removeIf(user -> user.getRoles().stream().anyMatch(role ->role.getId()==2));
        return lista.stream().map(this::getUserResponseDTO).collect(Collectors.toList());
    }

    @Override
    public User authenticate(LoginDto loginDto) {
        User emailUser = userRepository.findByEmail(loginDto.getEmail());

        if (emailUser != null) {
            loginDto.setPassword(Codificar.generateHash(loginDto.getPassword()));
            if (emailUser.getPassword().equals(loginDto.getPassword())) {
                return emailUser;
            }
        }
        throw new ApplicationException("Credenciais invalidas");
    }

    @Override
    public User findUserByEmail(String email){
       return userRepository.findByEmail(email);
    }

    @Override
    public void updateUser(String email,Role role) {
        System.out.println(email);
        User user = userRepository.findByEmail(email);
        user.getRoles().add(role);
        userRepository.save(user);
    }


    private User convertToUser(UserCreateDTO userCreateDTO){
        User user= new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());
        user.setCpf(userCreateDTO.getCpf());
        user.setSchool(userCreateDTO.getSchool());
        return  user;
    }

    private  UserResponseDTO getUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setSchool(user.getSchool());
        if (user.getRoles()!=null){
            List<String> roles = user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toList());
            userResponseDTO.setRoles(roles);
        }



        return userResponseDTO;
    }

    private boolean validateInsert(String cpf,String email){
        User emailUser = userRepository.findByEmail(email);

        if (emailUser != null) {
            throw new ApplicationException("Email já cadastrado");
        }

        User cpfUser = userRepository.findByCpf(cpf);

        if (cpfUser != null) {
            throw new ApplicationException("cpf já cadastrado");
        }
        return true;

    }

}
