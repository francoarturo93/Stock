package com.mvc.inventario.back.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.inventario.back.dtos.ProductoDto;
import com.mvc.inventario.back.dtos.ProductoNombreDto;
import com.mvc.inventario.back.entities.Categoria;
import com.mvc.inventario.back.entities.Producto;
import com.mvc.inventario.back.mapper.ProductoMapper;
import com.mvc.inventario.back.repositories.CategoriaRepository;
import com.mvc.inventario.back.repositories.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoMapper productoMapper;


    @Transactional(readOnly = true)
    public boolean existsByNombre(String nombre) {
        return productoRepository.existsByNombre(nombre);
    }
    /* CREAR */
    @Transactional
    public ProductoDto crearProducto(ProductoDto productoDto) {

        if (productoDto.getCategoria() == null || productoDto.getCategoria().getId() == null) {
            throw new IllegalArgumentException("El producto debe tener una categoría válida con un ID no nulo.");
        }
        
        Categoria categoria = categoriaRepository.findById(productoDto.getCategoria().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categoría con ID " + productoDto.getCategoria().getId() + " no encontrada"));
        
        Producto producto = new Producto();
        producto = productoMapper.productoDtoToProducto(productoDto);
        producto.setCategoria(categoria);
        producto.setStock(productoDto.getEntradas());

        producto = productoRepository.save(producto);
        ProductoDto resultado = productoMapper.productoToProductoDto(producto);
        return resultado;
    }
    /* ACTUALIZAR */
    @Transactional
    public Optional<Producto> update(Long id, Producto product) {
        Optional<Producto> productOptional = productoRepository.findById(id);
        if (productOptional.isPresent()) {
            Producto productDb = productOptional.get();
            
            // Actualizar campos básicos
            productDb.setNombre(product.getNombre());
            productDb.setPrecio(product.getPrecio());
            productDb.setEntradas(product.getEntradas());
            productDb.setSalidas(product.getSalidas());
            
            // Calcular y actualizar el stock (entradas - salidas)
            int nuevoStock = productDb.getEntradas() - product.getSalidas();
            productDb.setStock(nuevoStock);

            // Si se actualiza la categoría, buscarla en la base de datos
            if (product.getCategoria() != null && product.getCategoria().getId() != null) {
                Categoria categoria = categoriaRepository.findById(product.getCategoria().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Categoría con ID " + product.getCategoria().getId() + " no encontrada"));
                productDb.setCategoria(categoria);
            }

            // Guardar los cambios
            return Optional.of(productoRepository.save(productDb));
        }
        return Optional.empty();
    }
    /* BUSCAR */
    @Transactional(readOnly = true)
    public Page<ProductoNombreDto> buscarPorCategoriaConPaginacion(Long categoriaId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Producto> categoriaConPaginacion = productoRepository.findByCategoriaId(categoriaId, pageable);
        List<ProductoNombreDto> productoNombreList = categoriaConPaginacion.stream()
            .map(productoMapper::productoToProductoNombreDto)
                .collect(Collectors.toList());
        // return productoNombrePage;
            return new PageImpl<>(productoNombreList, pageable, categoriaConPaginacion.getTotalElements());

    }
    /* ELIMINAR */
    @Transactional
    public Optional<Producto> delete(Long id) {
        Optional<Producto> productOptional = productoRepository.findById(id);
        productOptional.ifPresent(productDb -> {
            productoRepository.delete(productDb);
        });
        return productOptional;
    }
    /* PAGINACION */
    @Transactional(readOnly = true)
    public Page<Producto> pageDeProductos(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productoRepository.findAll(pageable);
    }
    /* LISTAR */
    @Transactional(readOnly = true)
    public List<Producto> listaProductos() {
        return productoRepository.findAll();
    }


}
