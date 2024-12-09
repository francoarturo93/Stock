import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InventarioService } from 'src/app/services/inventario.service';

@Component({
  selector: 'app-page-productos-categoria',
  templateUrl: './page-productos-categoria.component.html',
  styleUrls: ['./page-productos-categoria.component.css']
})
export class PageProductosCategoriaComponent implements OnInit {

  categoriaForm: FormGroup;

  productos: any[] = [];
  categorias: any[] = [];
  page: number = 0;
  size: number = 3;
  totalPages: number = 0;

  constructor(private fb: FormBuilder, private inventarioService: InventarioService) {
    this.categoriaForm = this.fb.group({
      categoriaSeleccionada: [null] // Control para el id de la categoría
    });
  }
  ngOnInit(): void {
    this.cargarCategorias();
  }

  cargarCategorias(): void {
    this.inventarioService.listarCategorias().subscribe({
      next: (data: any) => {
        this.categorias = data;
        if (this.categorias.length > 0) {
          // Seleccionar la primera categoría por defecto
          this.categoriaForm.patchValue({ categoriaSeleccionada: this.categorias[0].id });
          this.cargarProductos();
        }
      },
      error: (error) => console.error('Error al cargar las categorías:', error),
    });
  }
  cargarProductos(): void {
    const categoriaSeleccionada = this.categoriaForm.value.categoriaSeleccionada;
    if (categoriaSeleccionada !== null) {
      this.inventarioService
        .pageProductosPorCategoria(categoriaSeleccionada, this.page, this.size)
        .subscribe({
          next: (data: any) => {
            this.productos = data.content;
            this.totalPages = data.totalPages;
          },
          error: (error) => console.error('Error al cargar los productos:', error),
        });
    }
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

  cambiarCategoria(): void {
    this.page = 0; // Reiniciar a la primera página al cambiar de categoría
    this.cargarProductos();
  }
}
