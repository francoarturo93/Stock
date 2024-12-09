package com.mvc.inventario.back.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.inventario.back.entities.RegistroSalida;

public interface RegistroSalidaRepository extends JpaRepository<RegistroSalida, Long>{
    Page<RegistroSalida> findByFechaSalidaBetween(String fechaInicio, String fechaFin, Pageable pageable);
}
