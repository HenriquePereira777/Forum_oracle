package com.DevPereira.ProjetoForum.domain.Resposta;

import com.DevPereira.ProjetoForum.domain.Topico.Topico;
import com.DevPereira.ProjetoForum.domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "respostas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resposta_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String mensagem;

    private LocalDateTime dataCriacao;

    //private boolean solucao;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
}