import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categoria } from 'src/app/models/models';
import { InventarioService } from 'src/app/services/inventario.service';

@Component({
  selector: 'app-actualizar-categorias',
  templateUrl: './actualizar-categorias.component.html',
  styleUrls: ['./actualizar-categorias.component.css']
})
export class ActualizarCategoriasComponent {
  categoriaForm!: FormGroup;
  categorias: Categoria[] = [];
  constructor(private fb: FormBuilder, private inventarioService: InventarioService) {
    this.categoriaForm = this.fb.group({
      categoriaId: ['', Validators.required], // ID del producto a actualizar
      nombre: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.cargarCategorias();
  }

  cargarCategorias(): void {
    this.inventarioService.listarCategorias().subscribe({
      next: (data) => this.categorias = data,
      error: (err) => console.error('Error al cargar categorías', err)
    });
  }

  cargarDatosCategoria(categoriaId: string): void {
    const producto = this.categorias.find(prod => prod.id === parseInt(categoriaId, 10));
    if (producto) {
      this.categoriaForm.patchValue({
        nombre: producto.nombre,
      });
    }
  }

  actualizarCategoria(): void {
    if (this.categoriaForm.valid) {
      const categoriaId = this.categoriaForm.value.categoriaId;
      const categoriaActualizado: Categoria = {
        id: categoriaId,
        nombre: this.categoriaForm.value.nombre,
      };

      this.inventarioService.actualizarCategoria(categoriaId, categoriaActualizado).subscribe({
        next: (response) => {
          alert('Categoria actualizado exitosamente!');
          console.log('Categoria actualizado:', response);
          this.categoriaForm.reset();
          this.cargarCategorias();
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
