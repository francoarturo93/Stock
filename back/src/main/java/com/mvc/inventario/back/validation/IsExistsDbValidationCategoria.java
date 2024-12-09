package com.mvc.inventario.back.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.mvc.inventario.back.services.CategoriaService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsExistsDbValidationCategoria implements ConstraintValidator<IsExistsDb, String>{

    @Autowired
    private CategoriaService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        if (service == null) {
            return true;
        }

        return !service.existsByNombre(value);
    }
    
}
