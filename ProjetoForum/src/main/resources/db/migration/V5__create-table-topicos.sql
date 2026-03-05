create table topicos
(
    topico_id    bigint       not null auto_increment,
    titulo       varchar(100) not null,
    mensagem     text,
    data_criacao datetime     not null default current_timestamp,
    status       varchar(50)  not null,
    autor_id     bigint       not null,
    curso_id     bigint       not null,
    constraint fk_autor
        foreign key (autor_id)
            references usuarios (usuario_id)
            on delete cascade,
    constraint fk_curso
        foreign key (curso_id)
            references cursos (curso_id),
    primary key (topico_id)


)