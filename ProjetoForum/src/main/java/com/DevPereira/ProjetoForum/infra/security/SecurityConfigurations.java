package com.DevPereira.ProjetoForum.infra.security;

import com.DevPereira.ProjetoForum.domain.Usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations  {

        // Injeta seu filtro JWT customizado
        @Autowired
        private  SecurityFilter securityFilter;


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    // ❌ Desativa proteção CSRF
                    // APIs REST com JWT não usam sessão → CSRF não é necessário
                    .csrf(csrf -> csrf.disable())

                    // ❌ Define que a aplicação NÃO usa sessão
                    // JWT → stateless → cada requisição carrega autenticação no token
                    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                    // Define regras de autorização
                    .authorizeHttpRequests(req -> req

                            // Permite acesso livre ao endpoint de login
                            .requestMatchers("/login").permitAll()
                          //  .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()

                            // Qualquer outro endpoint precisa estar autenticado
                            .anyRequest().authenticated()
                    )

                    // Adiciona seu filtro JWT antes do filtro padrão de login
                    // Isso permite validar token antes da autenticação padrão
                    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)

                    // Constrói configuração final
                    .build();
        }
        // ===============================
        // AUTHENTICATION MANAGER
        // ===============================
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{

            // Obtém AuthenticationManager configurado pelo Spring
            // Ele será usado no controller de login
            return configuration.getAuthenticationManager();
        }


        // ===============================
        // PASSWORD ENCODER
        // ===============================
        @Bean
        public PasswordEncoder passwordEncoder(){

            // Define BCrypt como algoritmo de criptografia
            // Usado para salvar senha e validar login
            return  new BCryptPasswordEncoder();
        }

    }

