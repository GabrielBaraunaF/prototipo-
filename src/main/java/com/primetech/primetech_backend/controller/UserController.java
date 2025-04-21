package com.primetech.primetech_backend.controller;

import com.primetech.primetech_backend.config.security.TokenAuthenticator;
import com.primetech.primetech_backend.dto.LoginDto;
import com.primetech.primetech_backend.dto.UserCreateDTO;
import com.primetech.primetech_backend.dto.UserResponseDTO;
import com.primetech.primetech_backend.dto.UserUpdateRequest;
import com.primetech.primetech_backend.entity.User;
import com.primetech.primetech_backend.facade.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @Operation(summary = "Create new User",
            description = "Create a new User",
            responses = {
                    @ApiResponse(responseCode = "201", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))
                    })
            }
    )
    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public UserResponseDTO save(@RequestBody UserCreateDTO user) {
        return userFacade.save(user);
    }


    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/list")
    public List<UserResponseDTO> findAll() {
        return userFacade.findAll();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginTo){

        User user = userFacade.authenticate(loginTo);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user,user.getPassword(), user.getAuthorities());
        token.setDetails(user);
        String tokenStr = TokenAuthenticator.addAuthentication(token);
        return ResponseEntity.ok().header(TokenAuthenticator.HEADER_STRING,tokenStr).body(user);
    }

    @Operation(summary = "Conceder permissao de colaborador",
            description = "permissao colaborador"
    )
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("update/user")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        userFacade.updateUser(userUpdateRequest.getEmail());
        return ResponseEntity.ok().body("deu certo");
    }


}
