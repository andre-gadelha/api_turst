package com.teamTregamos.api_TurSt.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Erros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(Exception e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Atividade com o ID especificado não encontrada.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException e) {

        var erros = e.getFieldErrors();
        return ResponseEntity.badRequest().body(erros);

    }

}
