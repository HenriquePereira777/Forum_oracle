package com.DevPereira.ProjetoForum.domain.Topico;

import com.DevPereira.ProjetoForum.domain.Usuario.Usuario;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

public record DadosListagemTopico(

        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String nomeAutor,
        String nomeCurso,
        String  status
) {
    public DadosListagemTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome(),
                topico.getStatus()
        );
    }

}
