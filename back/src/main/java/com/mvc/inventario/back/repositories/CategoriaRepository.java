package com.mvc.inventario.back.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.inventario.back.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNombre(String nombre);

    Optional<Categoria> findByNombre(String nombre);
}

