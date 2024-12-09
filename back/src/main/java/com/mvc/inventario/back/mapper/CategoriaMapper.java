package com.mvc.inventario.back.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.mvc.inventario.back.dtos.CategoriaDto;
import com.mvc.inventario.back.entities.Categoria;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoriaMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    CategoriaDto categoriaDtoToCategoria(Categoria categoria);
}
