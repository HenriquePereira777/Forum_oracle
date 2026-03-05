create table perfis (
                        perfil_id bigint not null auto_increment,
                        nome varchar(100) not null unique,
                        primary key (perfil_id)
);