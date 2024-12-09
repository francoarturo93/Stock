import { Component } from '@angular/core';
import { Producto } from 'src/app/models/models';
import { InventarioService } from 'src/app/services/inventario.service';

@Component({
  selector: 'app-eliminar-producto',
  templateUrl: './eliminar-producto.component.html',
  styleUrls: ['./eliminar-producto.component.css']
})
export class EliminarProductoComponent {

  productos: Producto[] = [];
  productoId: number | null = null;

  constructor(private inventarioService: InventarioService) {}

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.inventarioService.listarProductos().subscribe({
      next: (data) => this.productos = data,
      error: (err) => console.error('Error al cargar productos', err)
    });
  }

  eliminarProducto(): void {
    if (this.productoId) {
      if (confirm('¿Estás seguro de que deseas eliminar este producto?')) {
        this.inventarioService.eliminarProducto(this.productoId).subscribe({
          next: () => {
            alert('¡Producto eliminado exitosamente!');
            this.productoId = null;
            this.cargarProductos();
          },
          error: (err) => {
            alert('Error al eliminar el producto.');
            console.error(err);
          }
        });
      }
    } else {
      alert('Por favor, selecciona un producto para eliminar.');
    }
  }
}
