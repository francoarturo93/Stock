# Sistema de Inventario para "Lo Justo Sabor y Calidad" Fast Food

Este es un proyecto de gestión de inventario y control de stock que permite manejar productos, categorías, entradas y salidas en un sistema de inventario. El proyecto está desarrollado utilizando **Spring Boot** en el backend y **Angular** en el frontend.

## Tecnologías Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3.2.10**
  - `spring-boot-starter-data-jpa` - Para persistencia de datos usando JPA.
  - `spring-boot-starter-validation` - Validación de datos.
  - `spring-boot-starter-web` - Para crear servicios REST.
  - `spring-boot-devtools` - Herramientas de desarrollo.
- **MapStruct 1.5.2.Final** - Para mapear entidades y DTOs.
- **Apache Poi** - Para importar y exportar archivos `.xlsx`.
- **MySQL** - Base de datos relacional.
- **Lombok** - Para reducir el código repetitivo (Getters, Setters, etc.).
- **Maven** - Para la gestión de dependencias y ciclo de vida del proyecto.
  
### Frontend
- **Despliegue en `Netlify`**
  - URL: [lojusto.netlify.app](https://lojusto.netlify.app)
- **Angular 16.2.16**
  - Uso de componentes SPA para gestionar productos y controlar el stock.
  - Estilos en **CSS**.
  - Consumo de servicios REST usando `HttpClient`.
  - Uso de formularios reactivos y validaciones con `@angular/forms`.
  - **file-saver** para la descarga de archivos desde el frontend, como reportes o exportaciones de datos.
  - **Font Awesome** : Iconos visuales en botones, menús y estados del inventario, mejorando la experiencia del usuario.

### Requisitos previos
- **Java 17** o superior.
- **Node.js** (versión 16.20.2 o superior) y **npm** (versión 8.19.4 o superior).
- **MySQL** instalado y configurado.

## Funcionalidades

### Backend (Spring Boot)
- **CRUD de Productos y Categorías**: Crear, buscar, eliminar, listar y paginar productos y categorías.
- **Gestión de Stock**: Registrar entradas y salidas de productos, controlar el stock disponible.
- **Búsqueda por Fecha**: Buscar entradas y salidas de productos entre un rango de fechas.

### Frontend (Angular)
- **Formulario de Creación de Productos**: Crear productos con nombre, precio, entradas y categoría.
- **Control de Entradas y Salidas**: Registrar entradas y salidas de productos con la correspondiente actualización de stock.
- **Paginacion de Productos y Categorías**: Paginar productos y categorías.
- **Listado de Productos y Categorías**: Listar productos y categorías.



