package com.DevPereira.ProjetoForum.domain.Topico;

import com.DevPereira.ProjetoForum.domain.Curso.Curso;
import com.DevPereira.ProjetoForum.domain.Usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        Long autorId,
        @NotNull
        Long cursoId



) {
        public DadosCadastroTopico(Topico topico) {
                this(
                        topico.getTitulo(),
                        topico.getMensagem(),
                        topico.getAutor().getId(),
                        topico.getCurso().getId()
                );
        }
}
