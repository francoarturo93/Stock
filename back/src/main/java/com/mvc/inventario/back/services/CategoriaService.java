package com.mvc.inventario.back.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.inventario.back.dtos.CategoriaDto;
import com.mvc.inventario.back.entities.Categoria; 
import com.mvc.inventario.back.mapper.CategoriaMapper;
import com.mvc.inventario.back.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;
    
    @Transactional(readOnly = true)
    public boolean existsByNombre(String nombre) {
        return categoriaRepository.existsByNombre(nombre);
    }

    
    /* CREAR */
    @Transactional
    public CategoriaDto crearCategoria(CategoriaDto categoriaDto) {
        Categoria categoria = categoriaMapper.categoriaDtoToCategoria(categoriaDto);

        categoria = categoriaRepository.save(categoria);
        CategoriaDto categoriaDtoResultado = categoriaMapper.categoriaDtoToCategoria(categoria);
        return categoriaDtoResultado;
    }

    @Transactional(readOnly = true)
    public List<CategoriaDto> listaCategorias() {
        List<Categoria> categoriaList = categoriaRepository.findAll();
        List<CategoriaDto> categoriaResponse = categoriaList.stream()
                .map(categoriaMapper::categoriaDtoToCategoria)
                .collect(Collectors.toList());
        return categoriaResponse;
        // return categoriaRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Categoria> listCategorias() {
        List<Categoria> categoriaList = categoriaRepository.findAll();
        return categoriaList;
    }

    /* PAGINACION */
    @Transactional(readOnly = true)
    public Page<Categoria> pageDeCategorias(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return categoriaRepository.findAll(pageable);
    }
    /* Actualizar */
    @Transactional
    public Optional<Categoria> updateCategoria(Long id, Categoria categoriaActualizada) {
    Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoriaDb = categoriaOptional.get();

            // Actualizamos los campos permitidos
            categoriaDb.setNombre(categoriaActualizada.getNombre());

            // Guardamos los cambios
            return Optional.of(categoriaRepository.save(categoriaDb));
        }
        return Optional.empty(); // Si no se encuentra, devolvemos vacío
    }
    /* Eliminar */
    @Transactional
    public Optional<Categoria> delete(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        categoriaOptional.ifPresent(categoriaDb -> {
            categoriaRepository.delete(categoriaDb);
        });
        return categoriaOptional;
    }
}
