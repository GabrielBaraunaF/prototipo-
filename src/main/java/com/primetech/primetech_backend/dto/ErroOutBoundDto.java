package com.primetech.primetech_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErroOutBoundDto {

    private HttpStatus status;
    private String message;

}
