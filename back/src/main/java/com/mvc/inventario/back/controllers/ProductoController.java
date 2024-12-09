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

import com.mvc.inventario.back.dtos.ProductoDto;
import com.mvc.inventario.back.dtos.ProductoNombreDto;
import com.mvc.inventario.back.entities.Producto;
import com.mvc.inventario.back.exceptions.ValidationErrorHandler;
import com.mvc.inventario.back.services.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    /* CREAR */
    @PostMapping("/crear-productos")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody ProductoDto productoDto, BindingResult result) {
        if (result.hasFieldErrors()) {
            // Si hay errores de validación, devolvemos un BAD REQUEST con los detalles de los errores
            return ValidationErrorHandler.handleValidationErrors(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crearProducto(productoDto));
    }
    /* ACTUALIZAR */
    @PutMapping("/actualizar/{id}") 
    public ResponseEntity<?> update( @Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id ) {
        
        if (result.hasFieldErrors()) {
            return ValidationErrorHandler.handleValidationErrors(result);
        }

        Optional<Producto> productOptional = productoService.update(id, producto);
        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    /* ELIMINAR */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Producto> productOptional = productoService.delete(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    /* PAGINACION */
    @GetMapping("/pageproductos")
    public Page<Producto> obtenerProductos(@RequestParam int page, @RequestParam int size) {
        return productoService.pageDeProductos(page, size);
    }
    /* LISTAR */
    @GetMapping("/lista-productos")
    public List<Producto> listaProductos() {
        return productoService.listaProductos();
    }
    /* BUSCAR */
    @GetMapping("/productos-por-categoria")
    public ResponseEntity<?> productosPorCategoriaPaginados(
            @RequestParam Long categoriaId,
            @RequestParam int page,
            @RequestParam int size) {
        Page<ProductoNombreDto> productos = productoService.buscarPorCategoriaConPaginacion(categoriaId, page, size);
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(productos);
    }

}
