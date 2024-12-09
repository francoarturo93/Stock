# Sistema de Gestión de Inventario (Backend)

## Descripción del Proyecto

Este es un sistema de gestión de inventario desarrollado con Spring Boot, diseñado para ayudar a las empresas a gestionar y controlar su stock de productos de manera eficiente.

## Características Principales

### Gestión de Productos

- Crear nuevos productos
- Actualizar información de productos
- Eliminar productos
- Listar productos
- Búsqueda de productos por categoría
- Paginación de productos

### Gestión de Categorías

- Crear nuevas categorías
- Listar categorías
- Actualizar categorías
- Eliminar categorías
- Paginación de categorías

### Control de Stock

- Registro de entradas de producto
- Registro de salidas de producto
- Búsqueda de movimientos de stock por fecha
- Paginación de movimientos de stock

### Exportación e Importación

- Exportar productos a Excel
- Exportar registros de entradas a Excel
- Exportar registros de salidas a Excel
- Importar productos desde archivo Excel

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.2.11
- Spring Data JPA
- MySQL
- MapStruct (para mapeo de objetos)
- Apache POI (para manejo de Excel)
- Lombok (para reducción de código repetitivo)
- Validation API

## Endpoints Principales

### Productos

- `POST /api/producto/crear-productos`: Crear producto
- `PUT /api/producto/actualizar/{id}`: Actualizar producto
- `DELETE /api/producto/{id}`: Eliminar producto
- `GET /api/producto/pageproductos`: Paginación productos
- `GET /api/producto/lista-productos`: Listar productos
- `GET /api/producto/productos-por-categoria`: Buscar productos por categoría

### Categorías

- `POST /api/categoria/crear-categorias`: Crear categoría
- `GET /api/categoria/lista-categorias`: Listar categorías
- `GET /api/categoria/pagecategorias`: Paginación categorias
- `PUT /api/categoria/actualizar/{id}`: Actualizar categoría
- `DELETE /api/categoria/{id}`: Eliminar categoría

### Stock

- `POST /api/stock/entradas`: Registrar entrada de producto
- `POST /api/stock/salidas`: Registrar salida de producto
- `GET /api/stock/buscarEntradasPorFecha`: Buscar entradas por fecha
- `GET /api/stock/buscarSalidasPorFecha`: Buscar salidas por fecha
- `GET /api/stock/exportar`: Exportar productos
- `GET /api/stock/exportar-entradas`: Exportar entradas
- `GET /api/stock/exportar-salidas`: Exportar salidas
- `POST /api/stock/importar`: Importar productos
