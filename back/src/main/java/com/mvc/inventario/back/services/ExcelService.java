package com.mvc.inventario.back.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.inventario.back.entities.Categoria;
import com.mvc.inventario.back.entities.Producto;
import com.mvc.inventario.back.entities.RegistroEntrada;
import com.mvc.inventario.back.entities.RegistroSalida;
import com.mvc.inventario.back.repositories.CategoriaRepository;
import com.mvc.inventario.back.repositories.ProductoRepository;
import com.mvc.inventario.back.repositories.RegistroEntradaRepository;
import com.mvc.inventario.back.repositories.RegistroSalidaRepository;

@Service
public class ExcelService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private RegistroEntradaRepository registroEntradaRepository;

    @Autowired
    private RegistroSalidaRepository registroSalidaRepository;

    /* Exportar Salidas */
    public byte[] exportarSalidas() throws Exception {
        List<RegistroSalida> registroSalida = registroSalidaRepository.findAll();
        // Crea el archivo Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("RegistroSalidas");
        
        // Escribir el encabezado
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Producto");
        headerRow.createCell(1).setCellValue("Salidas(und)");
        headerRow.createCell(2).setCellValue("Fecha");

        // Escribir los datos
        int rowIndex = 1;
        for (RegistroSalida registro : registroSalida) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(registro.getProducto().getNombre());
            row.createCell(1).setCellValue(registro.getCantidadSalida());
            row.createCell(2).setCellValue(registro.getFechaSalida());
    
        }
        // Escribir el archivo a un ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        
        return outputStream.toByteArray();
    }
    /* Exportar Entradas */
    public byte[] exportarEntradas() throws Exception {
        List<RegistroEntrada> registroEntrada = registroEntradaRepository.findAll();
        // Crea el archivo Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("RegistroEntradas");
        
        // Escribir el encabezado
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Producto");
        headerRow.createCell(1).setCellValue("Entradas(und)");
        headerRow.createCell(2).setCellValue("Fecha");

        // Escribir los datos
        int rowIndex = 1;
        for (RegistroEntrada registro : registroEntrada) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(registro.getProducto().getNombre());
            row.createCell(1).setCellValue(registro.getCantidadEntrada());
            row.createCell(2).setCellValue(registro.getFechaEntrada());
    
        }
        // Escribir el archivo a un ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        
        return outputStream.toByteArray();
    }

    

    /* Exportar Productos */
    public byte[] exportarProductos() throws Exception {
        List<Producto> productos = productoRepository.findAll();

        // Crea el archivo Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Productos");
        
        // Escribir el encabezado
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nombre");
        headerRow.createCell(1).setCellValue("Precio");
        headerRow.createCell(2).setCellValue("Stock");
        headerRow.createCell(3).setCellValue("Entradas");
        headerRow.createCell(4).setCellValue("Salidas");
        headerRow.createCell(5).setCellValue("Categoría");

        // Escribir los datos
        int rowIndex = 1;
        for (Producto producto : productos) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(producto.getNombre());
            row.createCell(1).setCellValue(producto.getPrecio());
            row.createCell(2).setCellValue(producto.getStock());
            row.createCell(3).setCellValue(producto.getEntradas());

            // Manejar el caso de valores nulos en "salidas"
            row.createCell(4).setCellValue(producto.getSalidas() != null ? producto.getSalidas() : 0);

            row.createCell(5).setCellValue(producto.getCategoria().getNombre());
        }

        // Escribir el archivo a un ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }

    /* Importar Productos */
    public void importarProductos(MultipartFile file) throws Exception {
        // Abre el archivo Excel
        InputStream inputStream = file.getInputStream();
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // Supongamos que los datos están en la primera hoja

        List<Producto> productos = new ArrayList<>();

        // Itera desde la segunda fila (índice 1) para evitar el encabezado
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            // Validar que la fila no sea nula y que tenga contenido en la primera columna
            if (row == null || row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK) {
                continue; // Saltar filas vacías o sin datos
            }

            Producto producto = new Producto();

            // Validar cada celda antes de acceder a su valor
            if (row.getCell(0) != null) {
                producto.setNombre(row.getCell(0).getStringCellValue());
            }
            if (row.getCell(1) != null) {
                producto.setPrecio((int) row.getCell(1).getNumericCellValue());
            }
            if (row.getCell(2) != null) {
                producto.setStock((int) row.getCell(2).getNumericCellValue());
            }
            if (row.getCell(3) != null) {
                producto.setEntradas((int) row.getCell(3).getNumericCellValue());
            }
            if (row.getCell(4) != null) {
                producto.setSalidas((int) row.getCell(4).getNumericCellValue());
            }

            // Manejar la categoría
            if (row.getCell(5) != null) {
                String categoriaNombre = row.getCell(5).getStringCellValue();
                Categoria categoria = categoriaRepository.findByNombre(categoriaNombre)
                        .orElseGet(() -> {
                            Categoria nuevaCategoria = new Categoria();
                            nuevaCategoria.setNombre(categoriaNombre);
                            return categoriaRepository.save(nuevaCategoria);
                        });
                producto.setCategoria(categoria);
            }

            productos.add(producto);
        }

        // Guarda todos los productos en la base de datos
        productoRepository.saveAll(productos);
        workbook.close();
    }
}
