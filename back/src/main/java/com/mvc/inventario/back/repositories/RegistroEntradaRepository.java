package com.mvc.inventario.back.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.inventario.back.entities.RegistroEntrada;

public interface RegistroEntradaRepository extends JpaRepository<RegistroEntrada, Long> {
    Page<RegistroEntrada> findByFechaEntradaBetween(String fechaInicio, String fechaFin, Pageable pageable);
}
