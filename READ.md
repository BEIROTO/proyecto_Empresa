# Happiness&Co Asturias

Proyecto web para la gestión y difusión de eventos, cultura y experiencias en Asturias. Incluye una parte web informativa y una parte de programación en Java para la gestión interna de usuarios, eventos, galerías y favoritos.

## Descripción

Happiness&Co Asturias es una plataforma pensada para mostrar eventos culturales, gastronómicos y musicales de Asturias. El proyecto combina una interfaz web con páginas informativas, una aplicación en Java de consola y una base de datos con vistas SQL para organizar la información.

## Tecnologías usadas

- HTML5
- CSS3
- Tailwind CSS
- JavaScript
- Java
- SQL
- Figma

## Estructura del proyecto

- `index.html`: página principal.
- `eventos.html`: listado de eventos y filtros.
- `historial.html`: eventos ya celebrados.
- `sobrenosotros.html`: información sobre el proyecto y el equipo.
- `contacto.html`: formulario y datos de contacto.
- `fuentes.html`: fuentes de información y verificación.
- `eventos/`: páginas individuales de cada evento.
- `assets/`: imágenes, estilos y recursos gráficos.
- `Java`: clases del programa de consola.
- `Base de datos`: tablas, relaciones y vistas SQL.

## Parte web

La web está pensada para presentar los eventos de forma visual y ordenada. Tiene navegación entre secciones, selector de idioma, banners, tarjetas de eventos, fichas detalladas, formularios y galerÃ­as de imÃ¡genes.

## Parte Java

La aplicación en Java se usa para gestionar internamente:

- Usuarios.
- Eventos.
- GalerÃ­as.
- Favoritos.

El programa funciona con un menú de consola y usa estructuras como `HashMap` y `ArrayList` para guardar los datos en memoria.

## Base de datos

La base de datos organiza la información del proyecto mediante tablas y vistas. Se usan sentencias como:

- `CREATE TABLE` para crear tablas.
- `INSERT INTO` para añadir datos.
- `SELECT *` para consultar información.
- `CREATE OR REPLACE VIEW` para crear visitas virtuales.

Las vistas ayudan a mostrar información filtrada, como galerías antes de una fecha, favoritos de un usuario o imágenes relacionadas con un evento concreto.

## Objetivo

El objetivo del proyecto es crear una plataforma completa para descubrir, consultar y gestionar eventos de Asturias de una forma clara y visual, uniendo diseño web, programación y base de datos.

## Autor

Proyecto realizado como parte de los estudios de DAW.
