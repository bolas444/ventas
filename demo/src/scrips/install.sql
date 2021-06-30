drop table if exists usuario;
create table usuario(
    id          integer primary key,
    correo      varchar(64) not null unique,
    nombres     varchar(64) not null,
    apellidos   varchar(64) not null,
    pass        varchar(64) not null,
    status      bool default true
);


drop sequence if exists sec_usuario;
create sequence sec_usuario;


create table categoria(
    id      integer primary key,
    nombre  varchar(64) not null,
    status  bool default(true)
);

create table producto(
    id              integer primary key,
    nombre          varchar(128) not null,
    presentacion    varchar(32),
    status          bool default true,
    cantidad        integer not null, 
    categoria_id    integer not null,
    foreign key(categoria_id) references categoria(id) 
);

create sequence sec_categoria;
create sequence sec_producto;
