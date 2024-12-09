package com.mvc.inventario.back.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.inventario.back.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    boolean existsByNombre(String nombre);

    Page<Producto> findByCategoriaId(Long categoriaId, Pageable pageable);
    

}
