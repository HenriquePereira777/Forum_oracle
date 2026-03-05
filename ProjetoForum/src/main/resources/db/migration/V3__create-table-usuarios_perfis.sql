create table usuarios_perfis(
    usuario_id bigint not null ,
    perfil_id bigint not null ,
    primary key (usuario_id,perfil_id),
    constraint fk_usuario
                            foreign key (usuario_id)
                            references usuarios(usuario_id)
                            on delete cascade,
    constraint fk_perfil
                         foreign key (perfil_id)
                            references perfis(perfil_id)
                            on delete cascade

);