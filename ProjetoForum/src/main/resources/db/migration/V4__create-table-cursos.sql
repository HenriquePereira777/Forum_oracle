create table cursos(
    curso_id bigint not null  auto_increment,
    nome varchar(100) not null unique ,
    categoria varchar(150) not null,
    primary key (curso_id)
)