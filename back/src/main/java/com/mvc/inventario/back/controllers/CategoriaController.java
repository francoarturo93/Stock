package com.mvc.inventario.back.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.inventario.back.dtos.CategoriaDto;
import com.mvc.inventario.back.entities.Categoria;
import com.mvc.inventario.back.exceptions.ValidationErrorHandler;
import com.mvc.inventario.back.services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    /* CREAR */
    @PostMapping("/crear-categorias")
    public ResponseEntity<?> crearCategoria(@Valid @RequestBody CategoriaDto categoriaDto, BindingResult result) {
        if (result.hasFieldErrors()) {
            // Si hay errores de validación, devolvemos un BAD REQUEST con los detalles de los errores
            return ValidationErrorHandler.handleValidationErrors(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.crearCategoria(categoriaDto));
    }

    /* LISTAR */
    @GetMapping("/lista-categorias")
    public List<CategoriaDto> listaCategorias() {
        return categoriaService.listaCategorias();
    }
    @GetMapping("/list-categorias")
    public List<Categoria> listCategorias() {
        return categoriaService.listCategorias();
    }

    /* PAGINACION */
    @GetMapping("/pagecategorias")
    public Page<Categoria> obtenerCategorias(@RequestParam int page, @RequestParam int size) {
        return categoriaService.pageDeCategorias(page, size);
    }
    /* Actualizar */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoria, BindingResult result) {
        if (result.hasFieldErrors()) {
            return ValidationErrorHandler.handleValidationErrors(result); // Validar posibles errores
        }

        Optional<Categoria> categoriaOptional = categoriaService.updateCategoria(id, categoria);
        if (categoriaOptional.isPresent()) {
            return ResponseEntity.ok(categoriaOptional.get()); // Retornamos la categoría actualizada
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada");
    }

    /* Eliminar */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = categoriaService.delete(id);
        if (categoriaOptional.isPresent()) {
            return ResponseEntity.ok(categoriaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
