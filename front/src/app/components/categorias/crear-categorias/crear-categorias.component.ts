import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categoria } from 'src/app/models/models';
import { InventarioService } from 'src/app/services/inventario.service';

@Component({
  selector: 'app-crear-categorias',
  templateUrl: './crear-categorias.component.html',
  styleUrls: ['./crear-categorias.component.css']
})
export class CrearCategoriasComponent{
  categoriaForm!: FormGroup;

  constructor(private fb: FormBuilder, private inventarioService: InventarioService) {
    this.categoriaForm = this.fb.group({
      nombre: ['', Validators.required]
    });
  }
  // ngOnInit(): void {
  //   this.crearCategoria();
  // }

  crearCategoria(): void {
    if (this.categoriaForm.valid) {

      const categoriaData: Categoria = {
        nombre: this.categoriaForm.value.nombre

      };
      console.log(categoriaData);

      // Enviar la solicitud al backend
      this.inventarioService.crearCategoria(categoriaData).subscribe({
        next: (categoriaCreado) => {
          alert('¡Categoria creado exitosamente!');
          console.log('Producto creado', categoriaCreado);
          this.categoriaForm.reset(); // Limpiar el formulario tras el éxito
        },
        error: (error) => {
          alert('Error al crear el categoria. Por favor, intenta nuevamente.');
          console.error('Error al crear el categoria:', error);
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
