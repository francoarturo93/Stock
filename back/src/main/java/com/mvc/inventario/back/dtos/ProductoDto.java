package com.mvc.inventario.back.dtos;

import com.mvc.inventario.back.validation.IsExistsDb;

import lombok.Data;

@Data
public class ProductoDto {
    private Long id;

    @IsExistsDb
    private String nombre;
    private Integer precio;
    private Integer entradas;
    private CategoriaDto categoria; 
}
