import { Component, OnInit } from '@angular/core';
import { ProductoEntradaDTO } from 'src/app/models/models';
import { InventarioService } from 'src/app/services/inventario.service';

@Component({
  selector: 'app-registrar-entradas',
  templateUrl: './registrar-entradas.component.html',
  styleUrls: ['./registrar-entradas.component.css']
})
export class RegistrarEntradasComponent implements OnInit {

  productoEntrada: ProductoEntradaDTO = {
    productoId: 0,
    entradas: 0
  };
  mensaje: string = '';

  constructor(private inventarioService: InventarioService) { }

  ngOnInit(): void {
    this.agregarEntradas();
  }

  agregarEntradas() {
    if (this.productoEntrada.productoId > 0 && this.productoEntrada.entradas > 0) {
      this.inventarioService.agregarEntradas(this.productoEntrada).subscribe({
        next: (response) => {
          alert('¡Entrada de Producto creado exitosamente!');
          console.log('Producto creado', response);
          console.log('Producto entrada enviado', response);
        },
        error: (error) => {
          alert('Error al realizar entrada del producto. Por favor, intenta nuevamente.');
          console.error('Error al realizar entrada del producto:', error);
        },
        complete: () => {
          console.log('Proceso de creación completado.');
        }
      });
    } else {
      this.mensaje = 'Ingrese un ID de producto y número de entradas válido';
    }
  }
}
