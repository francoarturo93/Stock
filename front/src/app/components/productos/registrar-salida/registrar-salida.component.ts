import { Component, OnInit } from '@angular/core';
import { ProductoSalidaDTO } from 'src/app/models/models';
import { InventarioService } from 'src/app/services/inventario.service';

@Component({
  selector: 'app-registrar-salida',
  templateUrl: './registrar-salida.component.html',
  styleUrls: ['./registrar-salida.component.css']
})
export class RegistrarSalidaComponent implements OnInit {

  productoSalida: ProductoSalidaDTO = {
    productoId: 0,
    salidas: 0
  };
  mensaje: string = '';

  constructor(private inventarioService: InventarioService) { }

  ngOnInit(): void {
    this.agregarEntradas();
  }

  agregarEntradas() {
    if (this.productoSalida.productoId > 0 && this.productoSalida.salidas > 0) {
      this.inventarioService.registrarSaida(this.productoSalida).subscribe({
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
