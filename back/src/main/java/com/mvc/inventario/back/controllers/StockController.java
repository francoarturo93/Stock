package com.mvc.inventario.back.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.inventario.back.dtos.ProductoEntradaDto;
import com.mvc.inventario.back.dtos.ProductoSalidaDto;
import com.mvc.inventario.back.exceptions.ValidationErrorHandler;
import com.mvc.inventario.back.services.ExcelService;
import com.mvc.inventario.back.services.StockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/stock")
public class StockController {
    
    @Autowired
    private StockService stockService;

    @Autowired
    private ExcelService excelService;

    /* Entradas */
    @PostMapping("/entradas")
    public ResponseEntity<?> agregarEntradas(@Valid @RequestBody ProductoEntradaDto productoEntradaDTO,
            BindingResult result) {
        if (result.hasFieldErrors()) {
            // Si hay errores de validación, devolvemos un BAD REQUEST con los detalles de los errores
            return ValidationErrorHandler.handleValidationErrors(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.agregarEntradas(productoEntradaDTO));
    }

    @GetMapping("/buscarEntradasPorFecha")
    public ResponseEntity<?> buscarEntradasPorFecha(
            @RequestParam String fecha,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // Formato personalizado de la fecha "dd-MM-yy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");

        // Parsear el string a LocalDate con el formato definido
        LocalDate localDate = LocalDate.parse(fecha, formatter);

        // Obtener la fecha de inicio y fin para la búsqueda
        LocalDateTime fechaInicio = localDate.atStartOfDay(); // 00:00:00 del día especificado
        LocalDateTime fechaFin = localDate.plusDays(1).atStartOfDay(); // 00:00:00 del siguiente día

        // Llamar al servicio para buscar entradas con paginación
        return ResponseEntity.ok(stockService.buscarEntradasPorFechaEntre(fechaInicio, fechaFin, page, size));
    }

    /* Salidas*/ 
    @PostMapping("/salidas")
    public ResponseEntity<?> registrarSaida(@Valid @RequestBody ProductoSalidaDto productoSalidaDTO,
            BindingResult result) {
        if (result.hasFieldErrors()) {
            // Si hay errores de validación, devolvemos un BAD REQUEST con los detalles de los errores
            return ValidationErrorHandler.handleValidationErrors(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.restarSalidas(productoSalidaDTO));
    }

    @GetMapping("/buscarSalidasPorFecha")
    public ResponseEntity<?> buscarSalidasPorFecha(
            @RequestParam String fecha,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // Formato personalizado de la fecha "dd-MM-yy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");

        // Parsear el string a LocalDate con el formato definido
        LocalDate localDate = LocalDate.parse(fecha, formatter);

        // Obtener la fecha de inicio y fin para la búsqueda
        LocalDateTime fechaInicio = localDate.atStartOfDay(); // 00:00:00 del día especificado
        LocalDateTime fechaFin = localDate.plusDays(1).atStartOfDay(); // 00:00:00 del siguiente día

        // Llamar al servicio para buscar entradas con paginación
        return ResponseEntity.ok(stockService.buscarSalidasPorFechaEntre(fechaInicio, fechaFin, page, size));
    }
    /****************************************************************************************************************** */
    /* Exportar a Excel */
    @GetMapping("/exportar")
    //exportar productos
    public ResponseEntity<byte[]> exportarProductos() {
        try {
            byte[] excelContent = excelService.exportarProductos();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "productos.xlsx");

            return new ResponseEntity<>(excelContent, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Revisa qué error aparece aquí

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //exportar entradas
    @GetMapping("/exportar-entradas")
    public ResponseEntity<byte[]> exportarEntradas() {
        try {
            byte[] excelContent = excelService.exportarEntradas();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "registro_entradas.xlsx");

            return new ResponseEntity<>(excelContent, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Revisa qué error aparece aquí

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //exportar salidas
    @GetMapping("/exportar-salidas")
    public ResponseEntity<byte[]> exportarSalidas() {
        try {
            byte[] excelContent = excelService.exportarSalidas();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "registro_salidas.xlsx");

            return new ResponseEntity<>(excelContent, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Revisa qué error aparece aquí

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Importar a Excel */
    @PostMapping("/importar")
    public ResponseEntity<String> importarProductos(@RequestParam("file") MultipartFile file) {
        try {
            excelService.importarProductos(file);
            return ResponseEntity.ok("Archivo importado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al importar el archivo.");
        }
    }

}
