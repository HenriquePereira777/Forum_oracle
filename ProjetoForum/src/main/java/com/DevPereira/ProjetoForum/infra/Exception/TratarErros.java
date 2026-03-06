package com.DevPereira.ProjetoForum.infra.Exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class TratarErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {

        // Retorna resposta HTTP 404 sem corpo
        return ResponseEntity.status(404).body("Recurso nao encotrado");
    }
    // ===============================
    // ERRO 400 - VALIDAÇÃO DE CAMPOS
    // ===============================

    // Captura erro quando validação @Valid falha
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {

        // Obtém lista de erros de campo
        var erros = ex.getFieldErrors();

        // Retorna HTTP 400 com lista de erros convertida para DTO
        return ResponseEntity
                .badRequest()
                .body(erros.stream().map(DadosErroValidacao::new).toList());
    }
    // ===============================
    // ERRO 400 - JSON INVÁLIDO
    // ===============================

    // Captura erro quando JSON enviado é inválido ou mal formatado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {

        // Retorna HTTP 400 com mensagem do erro
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    // ===============================
    // ERRO 401 - CREDENCIAIS INVÁLIDAS
    // ===============================

    // Captura erro quando login/senha estão errados
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity tratarErroBadCredentials() {

        // Retorna HTTP 401 com mensagem amigável
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Credenciais inválidas");
    }

    // ===============================
    // ERRO 401 - FALHA DE AUTENTICAÇÃO
    // ===============================

    // Captura erros gerais de autenticação
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity tratarErroAuthentication() {

        // Retorna HTTP 401
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Falha na autenticação");
    }


    // ===============================
    // ERRO 403 - ACESSO NEGADO
    // ===============================

    // Captura erro quando usuário não tem permissão
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity tratarErroAcessoNegado() {

        // Retorna HTTP 403
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Acesso negado");
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidationException ex) {

        return  ResponseEntity.badRequest().body(ex.getMessage());
    }



    // ===============================
    // ERRO 500 - ERRO GENÉRICO
    // ===============================

    // Captura qualquer exceção não tratada
    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {

        // Retorna HTTP 500 com mensagem resumida
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro: " + ex.getLocalizedMessage());
    }


    // ===============================
    // DTO INTERNO PARA ERRO DE VALIDAÇÃO
    // ===============================

    // Record usado para retornar erro de campo e mensagem
    private record DadosErroValidacao(String campo, String mensagem) {

        // Construtor que recebe FieldError do Spring
        public DadosErroValidacao(FieldError erro) {

            // Extrai nome do campo e mensagem padrão
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
