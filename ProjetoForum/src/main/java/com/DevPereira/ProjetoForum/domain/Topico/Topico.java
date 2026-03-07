package com.DevPereira.ProjetoForum.domain.Topico;

import com.DevPereira.ProjetoForum.domain.Curso.Curso;
import com.DevPereira.ProjetoForum.domain.Resposta.Resposta;
import com.DevPereira.ProjetoForum.domain.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "topico_id")
        private Long id;

        private String titulo;

        @Column(columnDefinition = "TEXT")
        private String mensagem;

        private LocalDateTime dataCriacao;

        private String status;

        @ManyToOne
        @JoinColumn(name = "autor_id")
        private Usuario autor;

        @ManyToOne
        @JoinColumn(name = "curso_id")
        private Curso curso;

        @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
        private List<Resposta> respostas;

        public Topico(String titulo, String mensagem,  Usuario autor, Curso curso) {
                this.titulo = titulo;
                this.mensagem = mensagem;
                this.status = "ABERTO";
                this.autor = autor;
                this.curso = curso;
                this.dataCriacao = LocalDateTime.now();

        }
        public void atualizarInformacoes(DadosAtualizacaoTopico dados){
                if(dados.titulo() != null){
                        this.titulo = dados.titulo();
                }
                if(dados.mensagem() != null){
                        this.mensagem = dados.mensagem();
                }
                if(dados.status() != null){
                        this.status = dados.status();
                }
        }
}

