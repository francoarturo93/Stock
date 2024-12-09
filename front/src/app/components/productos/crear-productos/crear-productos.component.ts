import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categoria, Producto } from 'src/app/models/models';
import { InventarioService } from 'src/app/services/inventario.service';

@Component({
  selector: 'app-crear-productos',
  templateUrl: './crear-productos.component.html',
  styleUrls: ['./crear-productos.component.css']
})
export class CrearProductosComponent {

  productoForm!: FormGroup;
  categorias: Categoria[] = [];

  constructor(private fb: FormBuilder, private inventarioService: InventarioService) {
    this.productoForm = this.fb.group({
      nombre: ['', Validators.required],
      precio: ['', Validators.required],
      entradas: ['', Validators.required],
      categoria: ['', Validators.required] // Control para el id de la categoría
    });
  }

  // ngOnInit() llama a cargarCategorias() para asegurarte de que las categorías estén disponibles cuando el componente se muestre por primera vez.
  ngOnInit(): void {
    this.cargarCategorias();
  }

  cargarCategorias(): void {
    this.inventarioService.listarCategorias().subscribe((data) => {
      this.categorias = data;
    });
  }

  crearProducto(): void {
    if (this.productoForm.valid) {

      const productoData: Producto = {
        nombre: this.productoForm.value.nombre,
        precio: this.productoForm.value.precio,
        entradas: this.productoForm.value.entradas,
        categoria: {id: this.productoForm.value.categoria}

      };
      console.log(productoData);
      console.log(this.categorias);

      // Enviar la solicitud al backend
      this.inventarioService.crearProducto(productoData).subscribe({
        next: (productoCreado) => {
          alert('¡Producto creado exitosamente!');
          console.log('Producto creado', productoCreado);
          this.productoForm.reset(); // Limpiar el formulario tras el éxito
        },
        error: (error) => {
          alert('Error al crear el producto. Por favor, intenta nuevamente.');
          console.error('Error al crear el producto:', error);
        },
        complete: () => {
          console.log('Proceso de creación completado.');
        }
      });


    } else {
      alert('Por favor, completa todos los campos correctamente.');
      console.log('Formulario no válido');
    }
  }
}
