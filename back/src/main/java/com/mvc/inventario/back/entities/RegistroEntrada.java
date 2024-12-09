package com.mvc.inventario.back.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "entradas")
@Data
public class RegistroEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"registrosEntrada", "handler", "hibernateLazyInitializer"})//  elimina el bucle de esta entidad
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private int cantidadEntrada;

    private String fechaEntrada;
}
