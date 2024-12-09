import { Component } from '@angular/core';
import { InventarioService } from '../../../services/inventario.service';
import { saveAs } from 'file-saver'; // Importamos la librería file-saver


@Component({
  selector: 'app-page-productos',
  templateUrl: './page-productos.component.html',
  styleUrls: ['./page-productos.component.css']
})
export class PageProductosComponent {

  productos: any[] = [];
  archivoSeleccionado: File | null = null;
  mensaje: string | null = null;


  page = 0;
  size = 5;
  error: string = '';

  constructor(private inventarioService: InventarioService){}

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void{
    this.inventarioService.pageProductos(this.page, this.size)
      .subscribe({
        next: data => {
          this.productos = data.content;
        },
        error: error => {
          this.error = 'Error al conectar con la API';
          console.error('API error:', error); // Esto te ayudará a depurar
        }
      }
      );
  }

  siguientePagina(): void {
    this.page++;
    this.cargarProductos();
  }

  anteriorPagina(): void {
    if (this.page > 0) {
      this.page--;
      this.cargarProductos();
    }
  }

  // Metodo para exportar a Excel
  exportarExcel(): void {
    this.inventarioService.exportarProductos().subscribe({
      next: (data) => {
      // Convertimos los datos binarios del Excel a un Blob
      const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
      // Usamos la librería file-saver para descargar el archivo
      saveAs(blob, 'productos_exportar.xlsx');
    },
      error: (error) => {
        this.error = 'Error al exportar los productos';
        console.error('Error al exportar Excel:', error); // Esto te ayudará a depurar
      }
    });
  }

  // Metodo para importar un Excel
  importarExcel(): void{
    if (this.archivoSeleccionado) {
      this.inventarioService.importarProductos(this.archivoSeleccionado).subscribe({
        next: (response) => {
          alert("Archivo importado correctamente.")
          this.mensaje = response;
        },
        error: (err) => {
          console.error(err);
          this.mensaje = 'Error al subir el archivo.';
        }
      });
    }
  }
  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.archivoSeleccionado = input.files[0];
    }
  }
}
