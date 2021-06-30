 insert into usuario(id, correo,nombres,apellidos,pass)
 values(nextval('sec_usuario'), 'barenca@hotmail.com', 'hector', 'Barenca','123');


insert into categoria(id, nombre )
values (nextval('sec_categoria'),'Refrescos');

insert into categoria(id, nombre )
values (nextval('sec_categoria'),'aguas');

insert into producto(id,nombre,presetacion,cantidad,categoria_id)
values(nextval('sec_producto'),'coca cola','3lts',5,1);

create user ieso with password 'ieso'
create database iesodb with owner

create table ventas(
    id           integer primary key,
    productos      text,
    cantidadtotal   integer,
    descuento   integer,
    precio        decimal,
    fecha        date
);
create sequence sec_ventas;

insert into ventas(id,productos,cantidadtotal,descuento,precio,fecha)
values(nextval('sec_ventas'),'3 pepsi a 12 pesos. 1 boing a 8.5 pesos. 1 cloro a 32 pesos. 1 aosiosi a 100 pesos.',6,0,176.5,'2021/03/31');



pa las practicas 
create user practicas with password 'practicas'

create database prueba with owner

create table medicamentostodos(
    id          serial not null,
    laboratorio      varchar(100),
    forma     varchar(100),
    nombre   varchar(100),
    via        varchar(100),
    ingredientes        varchar(100)
);

copy medicamentostodos(laboratorio,forma,nombre,via,ingredientes) from '/home/mauricio/Escritorio/Practicas/webcrawler/venv/practica1/practica1_items2.csv' delimiter ',' csv header;


create table marcas(
    id          int,
    laboratorio      varchar(100),
    marca     varchar(100),
);


copy marcas(id,laboratorio,marca) from '/home/mauricio/Descargas/marcas.csv' delimiter ',' csv header;

create table medicamentos2(                                                                                                                                
    id          int,
    nombre      varchar(100),
    marca     varchar(100),
    descripcion      varchar(2000),
    presetacion     varchar(500),
    medicamento_marca_id     int
);


copy medicamentos2(id,nombre,marca,descripcion,presetacion,medicamento_marca_id) from '/home/mauricio/Descargas/medicamentos.csv' delimiter ',' csv header;


grant all privileges on all tables in schema public to practicas;
