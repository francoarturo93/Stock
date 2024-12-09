import { Component } from '@angular/core';
import { InventarioService } from 'src/app/services/inventario.service';

@Component({
  selector: 'app-page-categorias',
  templateUrl: './page-categorias.component.html',
  styleUrls: ['./page-categorias.component.css']
})
export class PageCategoriasComponent {

  categorias: any[] = [];

  page = 0;
  size = 5;
  error: string = '';

  constructor(private inventarioService: InventarioService){}

  ngOnInit(): void {
    this.cargarCategorias();
  }

  cargarCategorias(): void{
    this.inventarioService.pageCategorias(this.page, this.size)
      .subscribe({
        next: data => {
          this.categorias = data.content;
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
    this.cargarCategorias();
  }

  anteriorPagina(): void {
    if (this.page > 0) {
      this.page--;
      this.cargarCategorias();
    }
  }
}
