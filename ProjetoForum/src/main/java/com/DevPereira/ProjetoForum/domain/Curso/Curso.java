package com.DevPereira.ProjetoForum.domain.Curso;

import com.DevPereira.ProjetoForum.domain.Topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cursos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private Long id;

    private String nome;
    private String categoria;

    @OneToMany(mappedBy = "curso")
    private List<Topico> topicos;


}
