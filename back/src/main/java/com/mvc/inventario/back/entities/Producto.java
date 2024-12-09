package com.mvc.inventario.back.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mvc.inventario.back.validation.IsExistsDb;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @IsExistsDb
    private String nombre;
    private Integer precio;
    
    private Integer stock;
    private Integer entradas;
    private Integer salidas;

    @JsonIgnoreProperties({"productos", "handler", "hibernateLazyInitializer"})//  elimina el bucle de esta entidad
    @ManyToOne
    @JoinColumn(name = "categoria_id")//  aqui estara la clave foranea
    private Categoria categoria;//controla la relacion 

    @JsonIgnoreProperties({ "producto", "handler", "hibernateLazyInitializer" }) // elimina el bucle de esta entidad
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RegistroEntrada> registrosEntrada;
    
    @JsonIgnoreProperties({ "producto", "handler", "hibernateLazyInitializer" }) // elimina el bucle de esta entidad
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RegistroSalida> registrosSalida;
}
