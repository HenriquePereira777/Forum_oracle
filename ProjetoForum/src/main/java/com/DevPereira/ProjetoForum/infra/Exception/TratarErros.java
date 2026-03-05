package com.DevPereira.ProjetoForum.infra.Exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratarErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {

        // Retorna resposta HTTP 404 sem corpo
        return ResponseEntity.status(404).body("Recurso nao encotrado");
    }
}
