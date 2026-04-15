CREATE DATABASE IF NOT EXISTS happinessandco;
USE happinessandco;

-- ============================================
--  TABLAS
-- ============================================

CREATE TABLE Usuarios (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(300)        NOT NULL,
    email       VARCHAR(250)        NOT NULL UNIQUE,
    password    VARCHAR(250)        NOT NULL
);

CREATE TABLE Eventos (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    fecha       DATETIME            NOT NULL,
    titulo      VARCHAR(200)        NOT NULL,
    ubicacion   VARCHAR(300)        NOT NULL,
    descripcion TEXT
);

CREATE TABLE Galerias (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    titulo      VARCHAR(500)        NOT NULL,
    id_evento   INT                 NOT NULL,
    CONSTRAINT fk_galeria_evento
        FOREIGN KEY (id_evento) REFERENCES Eventos(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Imagenes_Galeria (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    titulo      VARCHAR(500)        NOT NULL,
    imagen      VARCHAR(500)        NOT NULL,
    id_galeria  INT                 NOT NULL,
    CONSTRAINT fk_imagen_galeria
        FOREIGN KEY (id_galeria) REFERENCES Galerias(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Favoritos (
    id_usuario  INT NOT NULL,
    id_evento   INT NOT NULL,
    PRIMARY KEY (id_usuario, id_evento),
    CONSTRAINT fk_fav_usuario
        FOREIGN KEY (id_usuario) REFERENCES Usuarios(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_fav_evento
        FOREIGN KEY (id_evento) REFERENCES Eventos(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- ============================================
--  USUARIOS
-- ============================================
INSERT INTO Usuarios (nombre, email, password) VALUES
('Carlos Mendez',   'carlos@email.com', SHA2('pass1234', 256)),
('Laura Gonzalez',  'laura@email.com',  SHA2('abcd5678', 256)),
('Marta Fernandez', 'marta@email.com',  SHA2('qwerty99',  256));

-- ============================================
--  EVENTOS PRÓXIMOS
-- ============================================
INSERT INTO Eventos (id, fecha, titulo, ubicacion, descripcion) VALUES
(1,  '2026-04-20 00:00:00', 'Romeria de San Marcos',           'Asturias', 'Tradicional romeria de primavera en honor a San Marcos.'),
(2,  '2026-04-20 00:00:00', 'Festival de Danza Tradicional',   'Asturias', 'Festival dedicado a las danzas folcloricas asturianas.'),
(3,  '2026-05-05 00:00:00', 'Festival de las Llamas',          'Oviedo',   'Gran festival musical en el corazon de Oviedo.'),
(4,  '2026-05-18 00:00:00', 'Feria del Cachopo',               'Asturias', 'Celebracion gastronomica del plato mas popular de Asturias.'),
(5,  '2026-05-22 00:00:00', 'Mercado Medieval de Aviles',      'Aviles',   'Mercado medieval con artesanos, juglares y espectaculos.'),
(6,  '2026-05-25 00:00:00', 'Mercado Gastronomico de Oviedo',  'Oviedo',   'Feria de productos gastronomicos locales y regionales.'),
(7,  '2026-05-28 00:00:00', 'Jazz en la Plaza de Aviles',      'Aviles',   'Conciertos de jazz al aire libre en la plaza principal.'),
(8,  '2026-06-01 00:00:00', 'Festival de la Fabada Asturiana', 'Asturias', 'Festival dedicado al plato estrella de la cocina asturiana.'),
(9,  '2026-06-05 00:00:00', 'Feria del Queso Cabrales',        'Asturias', 'Feria anual del afamado queso azul de los Picos de Europa.'),
(10, '2026-06-08 00:00:00', 'Romeria de los Picos de Europa',  'Asturias', 'Romeria tradicional con musica y baile entre montanas.'),
(11, '2026-06-15 00:00:00', 'Semana Negra de Gijon',           'Gijon',    'Festival literario y cultural de referencia internacional.'),
(12, '2026-06-25 00:00:00', 'Noche Flamenca en Gijon',         'Gijon',    'Espectaculo de flamenco en vivo bajo las estrellas de Gijon.');

-- ============================================
--  EVENTOS HISTORIAL
-- ============================================
INSERT INTO Eventos (id, fecha, titulo, ubicacion, descripcion) VALUES
(13, '2026-01-01 00:00:00', 'Concierto de Gaitas en Oviedo',              'Oviedo',   'Concierto de gaitas que dio la bienvenida al Ano Nuevo asturiano.'),
(14, '2026-01-01 00:00:00', 'Ruta de la Sidra Natural de Gijon',          'Gijon',    'Recorrido por los mejores llagares y siderias de Gijon.'),
(15, '2026-01-12 00:00:00', 'Jornadas del Pote Asturiano',                'Asturias', 'Jornadas gastronomicas dedicadas al tradicional pote asturiano.'),
(16, '2026-01-24 00:00:00', 'Exposicion de Arte Asturiano Contemporaneo', 'Asturias', 'Muestra de obras de artistas asturianos del siglo XXI.'),
(17, '2026-02-10 00:00:00', 'Festival de Teatro Clasico de Gijon',        'Gijon',    'Festival de representaciones teatrales clasicas en Gijon.'),
(18, '2026-03-01 00:00:00', 'Feria del Libro de Oviedo',                  'Oviedo',   'Feria anual del libro con presentaciones y firma de ejemplares.');

-- ============================================
--  GALERÍAS
-- ============================================
INSERT INTO Galerias (id, titulo, id_evento) VALUES
(1, 'Galeria Concierto de Gaitas en Oviedo',              13),
(2, 'Galeria Jornadas del Pote Asturiano',                15),
(3, 'Galeria Exposicion de Arte Asturiano Contemporaneo', 16);

-- ============================================
--  IMÁGENES DE LAS GALERÍAS
-- ============================================
INSERT INTO Imagenes_Galeria (titulo, imagen, id_galeria) VALUES
('Gaitas en el escenario',       'gaitas_01.jpg', 1),
('Publico disfrutando',          'gaitas_02.jpg', 1),
('Grupo folclorico asturiano',   'gaitas_03.jpg', 1),
('Detalle de gaita tradicional', 'gaitas_04.jpg', 1);

INSERT INTO Imagenes_Galeria (titulo, imagen, id_galeria) VALUES
('Presentacion del pote',  'pote_01.jpg', 2),
('Cocineros en accion',    'pote_02.jpg', 2),
('Degustacion popular',    'pote_03.jpg', 2);

INSERT INTO Imagenes_Galeria (titulo, imagen, id_galeria) VALUES
('Obra destacada sala principal',  'arte_01.jpg', 3),
('Visita guiada a la exposicion',  'arte_02.jpg', 3),
('Instalacion artistica central',  'arte_03.jpg', 3),
('Retrato de artista local',       'arte_04.jpg', 3);

-- ============================================
--  FAVORITOS
-- ============================================
INSERT INTO Favoritos (id_usuario, id_evento) VALUES
(1, 13),
(1, 15),
(1, 3);

INSERT INTO Favoritos (id_usuario, id_evento) VALUES
(2, 16),
(2, 18),
(2, 5),
(2, 11);

INSERT INTO Favoritos (id_usuario, id_evento) VALUES
(3, 14),
(3, 17),
(3, 7),
(3, 12);

-- ============================================
--  VISTAS
-- ============================================

CREATE OR REPLACE VIEW v_galerias_antes_feb AS
    SELECT g.id, g.titulo AS galeria, e.titulo AS evento, e.fecha
    FROM Galerias g
    JOIN Eventos e ON g.id_evento = e.id
    WHERE e.fecha < '2026-02-28 00:00:00';

CREATE OR REPLACE VIEW v_favoritos_usuario1 AS
    SELECT e.id, e.titulo, e.fecha, e.ubicacion
    FROM Favoritos f
    JOIN Eventos e ON f.id_evento = e.id
    WHERE f.id_usuario = 1;

CREATE OR REPLACE VIEW v_imagenes_galeria_evento15 AS
    SELECT ig.id, ig.titulo, ig.imagen
    FROM Imagenes_Galeria ig
    JOIN Galerias g ON ig.id_galeria = g.id
    WHERE g.id_evento = 15;

CREATE OR REPLACE VIEW v_favoritos_usuario2_post_feb AS
    SELECT e.id, e.titulo, e.fecha, e.ubicacion
    FROM Favoritos f
    JOIN Eventos e ON f.id_evento = e.id
    WHERE f.id_usuario = 2
      AND e.fecha > '2026-02-28 00:00:00';