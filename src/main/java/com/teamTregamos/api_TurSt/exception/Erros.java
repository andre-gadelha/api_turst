package com.teamTregamos.api_TurSt.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
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
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity tratarErro500(RuntimeException e) {

        // ManteNDO o log completo da stack trace para debugging interno
        e.printStackTrace();

        // Retorna uma mensagem de erro genérica e segura
        String mensagemSegura = "Ocorreu um erro interno inesperado. Consulte o administrador da API.";

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensagemSegura);

    }

    // DTO auxiliar para a resposta do erro
    public record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
