package com.mvc.inventario.back.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.mvc.inventario.back.dtos.RegistroEntradaResponseDTO;
import com.mvc.inventario.back.entities.RegistroEntrada;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RegistroEntradaMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "producto.id", target = "productoId") // Mapeo del ID del producto
    @Mapping(source = "producto.nombre", target = "nombre") // Mapeo del ID del producto
    @Mapping(source = "cantidadEntrada", target = "cantidadEntrada")
    @Mapping(source = "fechaEntrada", target = "fechaEntrada") // Formatea la fecha
    RegistroEntradaResponseDTO registroEntradaToResponseDTO(RegistroEntrada registroEntrada);
    
}
