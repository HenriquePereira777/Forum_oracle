package com.DevPereira.ProjetoForum.Controller;

import com.DevPereira.ProjetoForum.domain.Usuario.DadosAutenticacao;
import com.DevPereira.ProjetoForum.domain.Usuario.Usuario;
import com.DevPereira.ProjetoForum.infra.security.DadosTokenJWT;
import com.DevPereira.ProjetoForum.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    // Injeta o AuthenticationManager do Spring Security
    // Ele é quem realmente faz a autenticação
    @Autowired
    private AuthenticationManager manager;

    // Injeta seu serviço de geração de JWT
    @Autowired
    private TokenService tokenService;



    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        // Cria um objeto de autenticação com login e senha
        // Ainda não autenticado → apenas contém credenciais
        var authenticationToken =
                new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());

        // Aqui acontece a autenticação REAL
        // O Spring vai:
        // 1. Chamar UserDetailsService
        // 2. Buscar usuário no banco
        // 3. Comparar senha
        // 4. Se OK → retorna Authentication autenticado
        var authentication = manager.authenticate(authenticationToken);

        // Pega o usuário autenticado (principal)
        // E gera o JWT baseado nele
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        // Retorna token para o cliente
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
