package com.mvc.inventario.back.dtos;

import lombok.Data;

@Data
public class ProductoEntradaDto {
    // Id del producto a buscar
    private Long productoId;

    // Cantidad de productos que entran al stock
    private Integer entradas;

}
