create table usuarios(
                         usuario_id bigint not null auto_increment,
    nome varchar(100) not null ,
    email varchar(150) not null unique ,
    senha varchar(255) not null,
    primary key (usuario_id)
);