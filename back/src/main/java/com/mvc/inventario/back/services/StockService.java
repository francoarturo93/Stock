package com.mvc.inventario.back.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.inventario.back.dtos.ProductoEntradaDto;
import com.mvc.inventario.back.dtos.ProductoSalidaDto;
import com.mvc.inventario.back.dtos.RegistroEntradaResponseDTO;
import com.mvc.inventario.back.dtos.RegistroSalidaResponseDTO;
import com.mvc.inventario.back.entities.Producto;
import com.mvc.inventario.back.entities.RegistroEntrada;
import com.mvc.inventario.back.entities.RegistroSalida;
import com.mvc.inventario.back.mapper.RegistroEntradaMapper;
import com.mvc.inventario.back.mapper.RegistroSalidaMapper;
import com.mvc.inventario.back.repositories.ProductoRepository;
import com.mvc.inventario.back.repositories.RegistroEntradaRepository;
import com.mvc.inventario.back.repositories.RegistroSalidaRepository;

@Service
public class StockService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RegistroEntradaRepository registroEntradaRepository;
    
    @Autowired
    private RegistroSalidaRepository registroSalidaRepository;


    @Autowired
    private RegistroEntradaMapper registroEntradaMapper;
    
    @Autowired
    private RegistroSalidaMapper registroSalidaMapper;

    /* CONTROL DE STOCK */
    /* *Entradas */
    @Transactional
    public Producto agregarEntradas(ProductoEntradaDto productoEntradaDto) {
        
        Optional<Producto> productoOptional = productoRepository.findById(productoEntradaDto.getProductoId());

        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            producto.setEntradas(producto.getEntradas() + productoEntradaDto.getEntradas());
            producto.setStock(producto.getStock() + productoEntradaDto.getEntradas());
            // Registrar la entrada en la tabla RegistroEntrada
            RegistroEntrada registroEntrada = new RegistroEntrada();
            registroEntrada.setProducto(producto);
            registroEntrada.setCantidadEntrada(productoEntradaDto.getEntradas());
            // Formatear la fecha
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy ' a las' HH:mm");
            String formattedDate = now.format(formatter);

            registroEntrada.setFechaEntrada(formattedDate);
            registroEntradaRepository.save(registroEntrada);
            return productoRepository.save(producto);
        } else {
            throw new RuntimeException("Producto con ID '" + productoEntradaDto.getProductoId() + "' no encontrado.");
        }

    }
    /* *Salidas */
    @Transactional
    public Producto restarSalidas(ProductoSalidaDto productoSalidaDto) {

        Optional<Producto> productoOptional = productoRepository.findById(productoSalidaDto.getProductoId());

        if (productoOptional.isPresent()) {

            Producto producto = productoOptional.get();

            if (producto.getStock() >= productoSalidaDto.getSalidas()) {
                int salidasActuales = (producto.getSalidas() != null) ? producto.getSalidas() : 0;
                producto.setSalidas(salidasActuales + productoSalidaDto.getSalidas());
                producto.setStock(producto.getStock() - productoSalidaDto.getSalidas());

                RegistroSalida registroSalida = new RegistroSalida();
                registroSalida.setProducto(producto);
                registroSalida.setCantidadSalida(productoSalidaDto.getSalidas());
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy ' a las' HH:mm");
                String formattedDate = now.format(formatter);
                registroSalida.setFechaSalida(formattedDate);
                registroSalidaRepository.save(registroSalida);
                return productoRepository.save(producto);

            } else {
                throw new RuntimeException(
                        "Stock insuficiente para el producto con ID: " + productoSalidaDto.getProductoId());
            }

        } else {
            throw new RuntimeException("Producto con ID '" + productoSalidaDto.getProductoId() + "' no encontrado.");
        }

    }
    // Buscar productos por fecha de entrada 
    @Transactional(readOnly = true)
    public Page<RegistroEntradaResponseDTO> buscarEntradasPorFechaEntre(LocalDateTime fechaInicio,
            LocalDateTime fechaFin, int page, int size) {
        // Formatear las fechas como cadenas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        String fechaInicioStr = fechaInicio.format(formatter);
        String fechaFinStr = fechaFin.format(formatter);
        // Crear un objeto Pageable
        Pageable pageable = PageRequest.of(page, size);

        // Obtener los registros con paginación desde el repositorio
        Page<RegistroEntrada> registros = registroEntradaRepository.findByFechaEntradaBetween(fechaInicioStr, fechaFinStr, pageable);
        
        // Convertir la página de entidades en una página de DTOs
        return registros.map(registroEntradaMapper::registroEntradaToResponseDTO);
    }
    // Buscar productos por fecha de entrada 
    @Transactional(readOnly = true)
    public Page<RegistroSalidaResponseDTO> buscarSalidasPorFechaEntre(LocalDateTime fechaInicio, LocalDateTime fechaFin, int page, int size) {
        // Formatear las fechas como cadenas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        String fechaInicioStr = fechaInicio.format(formatter);
        String fechaFinStr = fechaFin.format(formatter);
        // Crear un objeto Pageable
        Pageable pageable = PageRequest.of(page, size);

        // Obtener los registros con paginación desde el repositorio
        Page<RegistroSalida> registros = registroSalidaRepository.findByFechaSalidaBetween(fechaInicioStr, fechaFinStr, pageable);

        // Convertir la página de entidades en una página de DTOs
        return registros.map(registroSalidaMapper::registroSalidaToResponseDTO);
    }
    
    
    
}
