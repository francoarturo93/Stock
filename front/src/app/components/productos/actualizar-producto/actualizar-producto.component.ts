import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categoria, Producto } from 'src/app/models/models';
import { InventarioService } from 'src/app/services/inventario.service';

@Component({
  selector: 'app-actualizar-producto',
  templateUrl: './actualizar-producto.component.html',
  styleUrls: ['./actualizar-producto.component.css']
})
export class ActualizarProductoComponent implements OnInit{
  productoForm!: FormGroup;
  productos: Producto[] = [];
  categorias: Categoria[] = [];

  constructor(private fb: FormBuilder, private inventarioService: InventarioService) {
    this.productoForm = this.fb.group({
      productoId: ['', Validators.required], // ID del producto a actualizar
      nombre: ['', Validators.required],
      precio: ['', Validators.required],
      entradas: ['', Validators.required],
      categoriaId: ['', Validators.required] // ID de la categoría
    });
  }

  ngOnInit(): void {
    this.cargarProductos();
    this.cargarCategorias();
  }

  cargarProductos(): void {
    this.inventarioService.listarProductos().subscribe({
      next: (data) => this.productos = data,
      error: (err) => console.error('Error al cargar productos', err)
    });
  }

  cargarCategorias(): void {
    this.inventarioService.listarCategorias().subscribe({
      next: (data) => this.categorias = data,
      error: (err) => console.error('Error al cargar categorías', err)
    });
  }

  cargarDatosProducto(productoId: string): void {
    const producto = this.productos.find(prod => prod.id === parseInt(productoId, 10));
    if (producto) {
      this.productoForm.patchValue({
        nombre: producto.nombre,
        precio: producto.precio,
        entradas: producto.entradas,
        categoriaId: producto.categoria?.id
      });
    }
  }

  actualizarProducto(): void {
    if (this.productoForm.valid) {
      const productoId = this.productoForm.value.productoId;
      const productoActualizado: Producto = {
        id: productoId,
        nombre: this.productoForm.value.nombre,
        precio: this.productoForm.value.precio,
        entradas: this.productoForm.value.entradas,
        salidas: 0,
        categoria: { id: this.productoForm.value.categoriaId }
      };

      this.inventarioService.actualizarProducto(productoId, productoActualizado).subscribe({
        next: (response) => {
          alert('¡Producto actualizado exitosamente!');
          console.log('Producto actualizado:', response);
          this.productoForm.reset();
          this.cargarProductos();
        },
        error: (error) => {
          alert('Error al actualizar el producto.');
          console.error('Error al actualizar el producto:', error);
        }
      });
    } else {
      alert('Por favor, completa todos los campos correctamente.');
    }
  }

}
