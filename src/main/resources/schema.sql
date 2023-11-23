create table caracteristicas
(
    id        bigint auto_increment
        primary key,
    nombre    varchar(255) null,
    url_icono varchar(255) null
);

create table categorias
(
    id          bigint auto_increment
        primary key,
    descripcion varchar(255) null,
    titulo      varchar(255) null,
    url_imagen  varchar(255) null
);

create table productos
(
    categoria_id bigint       null,
    producto_id  bigint auto_increment
        primary key,
    descripcion  varchar(255) null,
    nombre       varchar(255) null,
    constraint FK2fwq10nwymfv7fumctxt9vpgb
        foreign key (categoria_id) references categorias (id)
);

create table imagenes
(
    id          bigint auto_increment
        primary key,
    producto_id bigint       null,
    url         varchar(255) null,
    constraint FKhylof04iou23s0pb9ab6pbd4j
        foreign key (producto_id) references productos (producto_id)
);

create table producto_caracteristica
(
    caracteristica_id bigint not null,
    producto_id       bigint not null,
    constraint FK3vp3d65unfl7gokrmb6pjf651
        foreign key (producto_id) references productos (producto_id),
    constraint FKp7sxommck2r629h007c2mau4t
        foreign key (caracteristica_id) references caracteristicas (id)
);

create table usuarios
(
    id       bigint auto_increment
        primary key,
    apellido varchar(255)           null,
    email    varchar(255)           null,
    nombre   varchar(255)           null,
    password varchar(255)           null,
    role     enum ('ADMIN', 'USER') null
);