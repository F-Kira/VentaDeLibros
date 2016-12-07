--Creación de la tabla Nacionalidad.
CREATE TABLE public.nacionalidad
(
    id integer NOT NULL,
    descripcion character varying NOT NULL,
    CONSTRAINT pk_nacionalidad PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.nacionalidad
    OWNER to postgres;

--Creación de la tabla Sección.
CREATE TABLE public.seccion
(
    id integer NOT NULL,
    descripcion character varying NOT NULL,
    CONSTRAINT pk_seccion PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.seccion
    OWNER to postgres;

--Creación de la tabla Categoria.
CREATE TABLE public.categoria
(
    id integer NOT NULL,
    descripcion character varying NOT NULL,
    CONSTRAINT pk_categoria PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.categoria
    OWNER to postgres;

--Creación de la tabla Autor.
CREATE TABLE public.autor
(
    id integer NOT NULL,
    descripcion character varying NOT NULL,
    nacionalidad integer NOT NULL,
    CONSTRAINT pk_autor PRIMARY KEY (id),
    CONSTRAINT fk_nacionalidad FOREIGN KEY (nacionalidad)
        REFERENCES public.nacionalidad (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.autor
    OWNER to postgres;

--Creo una secuencia para mi tabla libro_info.
CREATE SEQUENCE public.secuencia_id
    INCREMENT 1
    START 1
    MINVALUE 1
;

ALTER SEQUENCE public.secuencia_id
    OWNER TO postgres;

--Creacion de la tabla libro_info.
CREATE TABLE public.libro_info
(
    id integer NOT NULL DEFAULT NEXTVAL('secuencia_id'),
    titulo character varying NOT NULL,
    descripcion character varying,
    categoria integer NOT NULL,
    fecha_publicacion date NOT NULL,
    seccion integer NOT NULL,
    autor integer NOT NULL,
    CONSTRAINT pk_libro_info PRIMARY KEY (id),
    CONSTRAINT fk_categoria FOREIGN KEY (categoria)
        REFERENCES public.categoria (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_seccion FOREIGN KEY (seccion)
        REFERENCES public.seccion (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_autor FOREIGN KEY (autor)
        REFERENCES public.autor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.libro_info
    OWNER to postgres;

---------------------------------------------------------------------------------

--Inserción de datos en la tabla categoria.

insert into categoria (id, descripcion) values (1, 'Ciencia ficción');
insert into categoria (id, descripcion) values (2, 'Aventura');
insert into categoria (id, descripcion) values (3, 'Terror');
insert into categoria (id, descripcion) values (4, 'Policial');
insert into categoria (id, descripcion) values (5, 'Romántica');
insert into categoria (id, descripcion) values (6, 'Investigación');
insert into categoria (id, descripcion) values (7, 'Infantil');
insert into categoria (id, descripcion) values (8, 'Biografia');
insert into categoria (id, descripcion) values (9, 'Enciclopedia/Manual');
insert into categoria (id, descripcion) values (10, 'Politica');
insert into categoria (id, descripcion) values (11, 'Filosofia');
insert into categoria (id, descripcion) values (12, 'Psicologia');
insert into categoria (id, descripcion) values (13, 'Religión');
insert into categoria (id, descripcion) values (14, 'Otros temas');

--Inserción de datos en la tabla sección.
insert into seccion (id, descripcion) values (1, 'A1');
insert into seccion (id, descripcion) values (2, 'A2');
insert into seccion (id, descripcion) values (3, 'A3');
insert into seccion (id, descripcion) values (4, 'A4');
insert into seccion (id, descripcion) values (5, 'B1');
insert into seccion (id, descripcion) values (6, 'B2');
insert into seccion (id, descripcion) values (7, 'B3');
insert into seccion (id, descripcion) values (8, 'B4');
insert into seccion (id, descripcion) values (9, 'C1');
insert into seccion (id, descripcion) values (10, 'C2');
insert into seccion (id, descripcion) values (11, 'C3');

--Inserción de datos en la tabla nacionalidad.

insert into nacionalidad (id, descripcion) values (1, 'Alemania');
insert into nacionalidad (id, descripcion) values (2, 'Argentina');
insert into nacionalidad (id, descripcion) values (3, 'Australia');
insert into nacionalidad (id, descripcion) values (4, 'Austria');
insert into nacionalidad (id, descripcion) values (5, 'Brasil');
insert into nacionalidad (id, descripcion) values (6, 'Chile');
insert into nacionalidad (id, descripcion) values (7, 'Colombia');
insert into nacionalidad (id, descripcion) values (8, 'Dinamarca');
insert into nacionalidad (id, descripcion) values (9, 'Ecuador');
insert into nacionalidad (id, descripcion) values (10, 'España');
insert into nacionalidad (id, descripcion) values (11, 'Estados Unidos');
insert into nacionalidad (id, descripcion) values (12, 'Finlandia');
insert into nacionalidad (id, descripcion) values (13, 'Francia');
insert into nacionalidad (id, descripcion) values (14, 'Inglaterra');
insert into nacionalidad (id, descripcion) values (15, 'Irlanda');
insert into nacionalidad (id, descripcion) values (16, 'Paraguay');
insert into nacionalidad (id, descripcion) values (17, 'Perú');
insert into nacionalidad (id, descripcion) values (18, 'Polonia');
insert into nacionalidad (id, descripcion) values (19, 'Rusia');
insert into nacionalidad (id, descripcion) values (20, 'Suecia');
insert into nacionalidad (id, descripcion) values (21, 'Suiza');
insert into nacionalidad (id, descripcion) values (22, 'Uruguay');
insert into nacionalidad (id, descripcion) values (23, 'Ucrania');
insert into nacionalidad (id, descripcion) values (24, 'Nigeria');
insert into nacionalidad (id, descripcion) values (25, 'México');
insert into nacionalidad (id, descripcion) values (26, 'Japón');

--Inserción de datos en la tabla autor.

insert into autor (id, descripcion, nacionalidad) values (1, 'Paulo Cohelo', 5);
insert into autor (id, descripcion, nacionalidad) values (2, 'Gabriel Garcia Márquez', 7);
insert into autor (id, descripcion, nacionalidad) values (3, 'William Shakespeare', 14);
insert into autor (id, descripcion, nacionalidad) values (4, 'Isabell Allende', 17);
insert into autor (id, descripcion, nacionalidad) values (5, 'Jhon Katzenbach', 11);
insert into autor (id, descripcion, nacionalidad) values (6, 'Mario Vargas', 17);
insert into autor (id, descripcion, nacionalidad) values (7, 'Miguel de Cervantes', 10);
insert into autor (id, descripcion, nacionalidad) values (8, 'Patrick Suskin', 1);
insert into autor (id, descripcion, nacionalidad) values (9, 'Roland E. Larson', 11);
insert into autor (id, descripcion, nacionalidad) values (10, 'Suzanne Collins', 11);
insert into autor (id, descripcion, nacionalidad) values (11, 'Dan Brown', 11);
insert into autor (id, descripcion, nacionalidad) values (12, 'Charles Dickens', 14);
insert into autor (id, descripcion, nacionalidad) values (13, 'Gabriela Mistral', 6);
insert into autor (id, descripcion, nacionalidad) values (14, 'Marguerite Duras', 13);

--Inserción de datos en la tabla libro_info.

insert into libro_info (titulo, categoria, fecha_publicacion, seccion, autor)
values ('El Alquimista', 2, '1996-01-01', 1, 1);
insert into libro_info (titulo, categoria, fecha_publicacion, seccion, autor)
values ('La ciudad y los perros', 1, '1996-12-14', 1, 1);
insert into libro_info (titulo, categoria, fecha_publicacion, seccion, autor)
values ('Memorias de una Geisha', 2, '1996-12-14', 1, 1);
insert into libro_info (titulo, categoria, fecha_publicacion, seccion, autor)
values ('El Perfume', 14, '1996-12-14', 1, 1);
insert into libro_info (titulo, categoria, fecha_publicacion, seccion, autor)
values ('El Psicoanalista', 2, '1996-12-14', 1, 1);
insert into libro_info (titulo, categoria, fecha_publicacion, seccion, autor)
values ('El código Da Vinci', 3, '1996-12-14', 1, 1);
insert into libro_info (titulo, categoria, fecha_publicacion, seccion, autor)
values ('El coronel no tiene quien le escriba', 8, '1996-12-14', 1, 1);
insert into libro_info (titulo, categoria, fecha_publicacion, seccion, autor)
values ('A orillas del río Piedra me sente y llore', 4,'1996-12-14', 1, 1);
insert into libro_info (titulo, categoria, fecha_publicacion, seccion, autor)
values ('La casa de los espiritus', 9, '1996-12-14', 1, 1);
insert into libro_info (titulo, categoria, fecha_publicacion, seccion, autor)
values ('El Peregrino', 6, '1996-12-14', 1, 1);
insert into libro_info (titulo, categoria, fecha_publicacion, seccion, autor)
values ('Crónica de una muerte anunciada', 3, '1996-12-14', 1, 1);
insert into libro_info (titulo, categoria, fecha_publicacion, seccion, autor)
values ('El Principito', 12, '1996-12-14', 1, 1);
