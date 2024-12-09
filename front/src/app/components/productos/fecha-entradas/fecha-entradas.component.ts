import { formatDate } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { InventarioService } from 'src/app/services/inventario.service';
import { saveAs } from 'file-saver'; // Importamos la librería file-saver

@Component({
  selector: 'app-fecha-entradas',
  templateUrl: './fecha-entradas.component.html',
  styleUrls: ['./fecha-entradas.component.css'],
})
export class FechaEntradasComponent {
  // Formulario para capturar la fecha
  fechaForm: FormGroup;

  // Para almacenar los resultados de la búsqueda
  entradas: any[] = [];
  page = 0;
  size = 5;
  totalPages = 0;
  error: string = '';

  constructor(private inventarioService: InventarioService) {
    // Inicializar el formulario con el campo de la fecha
    this.fechaForm = new FormGroup({
      fecha: new FormControl(''), // Inicializa el campo vacío
    });
  }
  ngOnInit(): void {
    this.buscarEntradasPorFecha();
  }

  // Función para buscar las entradas por fecha
  buscarEntradasPorFecha(page: number = 0) {
    const fecha = this.fechaForm.get('fecha')?.value;

    if (!fecha || fecha.trim() === '') {
      console.error('Fecha no válida');
      return;
    }
    // Formatear la fecha en "dd-MM-yy" (que espera el backend)
    const fechaFormateada = formatDate(fecha, 'dd-MM-yy', 'en-US');

    console.log(fechaFormateada);

    // Hacer la solicitud HTTP al backend
    this.inventarioService.buscarEntradasPorFecha(fechaFormateada,this.page,this.size).subscribe({
      next: (response) => {
        this.entradas = response.content;
        this.page = response.number;
        this.totalPages = response.totalPages;
      },
      error: (error) => {
        console.error('Error al buscar la fecha actual:', error);
      },
      complete: () => {
        console.log('Proceso de creación completado.');
      },
    });
  }

  // Cambiar de página
  siguientePagina(): void {
    this.page++;
    this.buscarEntradasPorFecha();
  }

  anteriorPagina(): void {
    if (this.page > 0) {
      this.page--;
      this.buscarEntradasPorFecha();
    }
  }

  // Metodo para exportar a Excel
  exportarExcel(): void {
    this.inventarioService.exportarEntradas().subscribe({
      next: (data) => {
      // Convertimos los datos binarios del Excel a un Blob
      const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
      // Usamos la librería file-saver para descargar el archivo
      saveAs(blob, 'registro_entradas.xlsx');
    },
      error: (error) => {
        this.error = 'Error al exportar los productos';
        console.error('Error al exportar Excel:', error); // Esto te ayudará a depurar
      }
    });
  }

}
