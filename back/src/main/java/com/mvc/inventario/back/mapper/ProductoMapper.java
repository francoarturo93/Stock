package com.mvc.inventario.back.mapper;

import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.mvc.inventario.back.dtos.ProductoDto;
import com.mvc.inventario.back.dtos.ProductoNombreDto;
import com.mvc.inventario.back.entities.Producto;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductoMapper {
        
        // Mapea de ProductoDto a Producto(target - source)
        //@Mapping(source = "categoria", target = "categoria") // Mapea categoriaDto a categoria
        Producto productoDtoToProducto(ProductoDto productoDto);
        // (target          -           source)

        // Mapea de Producto a ProductoDto
        //@Mapping(source = "categoria", target = "categoria") // Mapea categoria a categoriaDto
        ProductoDto productoToProductoDto(Producto producto);

        ProductoNombreDto productoToProductoNombreDto(Producto producto);
}
