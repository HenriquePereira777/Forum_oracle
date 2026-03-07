package com.DevPereira.ProjetoForum.domain.Topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(String titulo, String mensagem, LocalDateTime dataCriacao, String status, String autor, String curso) {
    public DadosDetalhamentoTopico(Topico detalhamento) {
        this(
                detalhamento.getTitulo(),
                detalhamento.getMensagem(),
                detalhamento.getDataCriacao(),
                detalhamento.getStatus(),
                detalhamento.getAutor().getNome(),
                detalhamento.getCurso().getNome()

        );
    }
}
