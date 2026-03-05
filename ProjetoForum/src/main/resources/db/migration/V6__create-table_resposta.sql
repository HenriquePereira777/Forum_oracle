create table respostas(
    resposta_id bigint not null auto_increment,
    mensagem text not null,
    data_criacao datetime not null default current_timestamp,
    autor_id bigint not null ,
    topico_id bigint not null ,
    constraint fk_autor_id
                     foreign key (autor_id)
                     references usuarios(usuario_id)
                     on delete cascade ,
    constraint  fk_topico_id
                     foreign key (topico_id)
                     references topicos(topico_id)
                     on delete cascade,
    primary key (resposta_id)


)