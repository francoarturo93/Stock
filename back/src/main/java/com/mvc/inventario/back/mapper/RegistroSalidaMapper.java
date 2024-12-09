package com.mvc.inventario.back.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.mvc.inventario.back.dtos.RegistroSalidaResponseDTO;
import com.mvc.inventario.back.entities.RegistroSalida;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RegistroSalidaMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "producto.id", target = "productoId") // Mapeo del ID del producto
    @Mapping(source = "producto.nombre", target = "nombre") // Mapeo del ID del producto
    @Mapping(source = "cantidadSalida", target = "cantidadSalida")
    @Mapping(source = "fechaSalida", target = "fechaSalida") 
    RegistroSalidaResponseDTO registroSalidaToResponseDTO(RegistroSalida registroSalida);
}
