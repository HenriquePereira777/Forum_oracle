package com.DevPereira.ProjetoForum.infra.security;

import com.DevPereira.ProjetoForum.domain.Usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    // Injeta serviço de token (gera e valida JWT)
    @Autowired
    private TokenService tokenService;

    // Injeta repositório de usuários
    @Autowired
    private UsuarioRepository repository;

    // ===============================
    // MÉTODO PRINCIPAL DO FILTRO
    // ===============================
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Recupera token da requisição
        var tokenJWT = recuperarToken(request);

        // Se existir token
        if(tokenJWT !=  null) {

            // Extrai subject (login do usuário) do token
            var subject = tokenService.getSubject(tokenJWT);

            // Busca usuário no banco
            var usuario = repository.findByLogin(subject);

            // Cria objeto de autenticação do Spring
            var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());

            // Coloca usuário autenticado no contexto de segurança
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continua fluxo da requisição
        filterChain.doFilter(request,response);
    }


    // ===============================
    // MÉTODO PARA RECUPERAR TOKEN
    // ===============================
    private String  recuperarToken(HttpServletRequest request) {

        // Obtém header Authorization
        var authorizationHeader = request.getHeader("Authorization");

        // Se existir
        if (authorizationHeader != null) {

            // Remove "Bearer" do início do token
            return authorizationHeader.replace("Bearer ", "").trim();
        }

        // Se não existir → retorna null
        return null;
    }

}
