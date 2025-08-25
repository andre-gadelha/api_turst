package com.teamTregamos.api_TurSt.model.infra.erros;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Erros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(Exception e) {
        return ResponseEntity.notFound().build();
    }

}
