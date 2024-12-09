import { Component } from '@angular/core';
import { Categoria } from 'src/app/models/models';
import { InventarioService } from 'src/app/services/inventario.service';

@Component({
  selector: 'app-eliminar-categorias',
  templateUrl: './eliminar-categorias.component.html',
  styleUrls: ['./eliminar-categorias.component.css']
})
export class EliminarCategoriasComponent {
  categorias: Categoria[] = [];
  categoriaId: number | null = null;

  constructor(private inventarioService: InventarioService) {}

  ngOnInit(): void {
    this.cargarCategorias();
  }

  cargarCategorias(): void {
    this.inventarioService.listarCategorias().subscribe({
      next: (data) => this.categorias = data,
      error: (err) => console.error('Error al cargar categorías', err)
    });
  }

  eliminarCategoria(): void {
    if (this.categoriaId) {
      if (confirm('¿Estás seguro de que deseas eliminar esta Categoria?')) {
        this.inventarioService.eliminarCategoria(this.categoriaId).subscribe({
          next: () => {
            alert('Categoria eliminado exitosamente!');
            this.categoriaId = null;
            this.cargarCategorias();
          },
          error: (err) => {
            alert('Error al eliminar la Categoria.');
            console.error(err);
          }
        });
      }
    } else {
      alert('Por favor, selecciona un producto para eliminar.');
    }
  }
}
