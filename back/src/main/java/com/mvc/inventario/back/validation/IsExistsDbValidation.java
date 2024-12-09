package com.mvc.inventario.back.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.mvc.inventario.back.services.ProductoService;

@Component
public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String>{

    @Autowired
    private ProductoService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        if (service == null) {
            return true;
        }

        return !service.existsByNombre(value);
    }
    
}
