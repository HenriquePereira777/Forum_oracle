package com.DevPereira.ProjetoForum.infra.security;

import com.DevPereira.ProjetoForum.domain.Usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Injeta segredo definido no application.properties
    @Value("${api.security.token.secret}")
    private String secret;


    // ===============================
    // MÉTODO PARA GERAR TOKEN
    // ===============================
    public  String gerarToken(Usuario usuario){


        try{

            // Define algoritmo de assinatura (HMAC256 + secret)
            var algoritmo = Algorithm.HMAC256(secret);

            // Cria token JWT
            return JWT.create()

                    // Define quem emitiu o token
                    .withIssuer("Api ForumRub")

                    // Define subject (quem é o usuário)
                    .withSubject(usuario.getLogin())

                    // Define data de expiração
                    .withExpiresAt(dataExpiracao())

                    // Assina token com algoritmo
                    .sign(algoritmo);

        }catch (JWTCreationException exception){

            // Se falhar criação → lança erro
            throw  new RuntimeException("erro ao gerar token", exception);
        }
    }


    // ===============================
    // MÉTODO PARA VALIDAR TOKEN
    // ===============================
    public String getSubject(String tokenJWT) {
        try {

            // Define algoritmo novamente (mesmo usado na criação)
            var algoritmo = Algorithm.HMAC256(secret);

            // Valida token
            return JWT.require(algoritmo)

                    // Exige mesmo issuer
                    .withIssuer("Api ForumRub")

                    // Constrói verificador
                    .build()

                    // Valida token
                    .verify(tokenJWT)

                    // Extrai subject (login)
                    .getSubject();

        } catch (JWTVerificationException exception) {

            // Token inválido ou expirado
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }


    // ===============================
    // DATA DE EXPIRAÇÃO
    // ===============================
    public Instant dataExpiracao(){

        // Token expira em 2 horas
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

}
