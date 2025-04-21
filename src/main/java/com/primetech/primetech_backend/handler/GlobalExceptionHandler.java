package com.primetech.primetech_backend.handler;

import com.primetech.primetech_backend.dto.ErroOutBoundDto;
import com.primetech.primetech_backend.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroOutBoundDto> handlerException(Exception ex) {

        ErroOutBoundDto errorOutbound = new ErroOutBoundDto(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
        log.error("INTERNAL_SERVER_ERROR: " + ex.getMessage());
        log.error(ex.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorOutbound);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErroOutBoundDto> handlerResourceNotFoundException(ApplicationException ex) {
        ErroOutBoundDto errorOutbound = new ErroOutBoundDto(HttpStatus.BAD_REQUEST, ex.getMessage());
        log.error("BAD_REQUEST: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorOutbound);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErroOutBoundDto> handleAccessDeniedException(AccessDeniedException ex) {
        ErroOutBoundDto errorOutbound = new ErroOutBoundDto(HttpStatus.FORBIDDEN, ex.getMessage());
        log.error("Forbidden " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorOutbound);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroOutBoundDto> handlerMethodArgumentNotValidException (MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        String erroMessage = "";

        for (FieldError fieldError : fieldErrors) {
            erroMessage = "Field " + fieldError.getField() + " "
                    + fieldError.getDefaultMessage();
            break;
        }

        ErroOutBoundDto errorOutbound = new ErroOutBoundDto(HttpStatus.BAD_REQUEST, erroMessage);
        log.error("BAD_REQUEST: " + erroMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorOutbound);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErroOutBoundDto> handlerNoResourceFoundException(NoResourceFoundException ex) {
        ErroOutBoundDto errorOutbound = new ErroOutBoundDto(HttpStatus.NOT_FOUND, "Endpoint Inexistente");
        log.error("NOT_FOUND: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorOutbound);
    }
}

