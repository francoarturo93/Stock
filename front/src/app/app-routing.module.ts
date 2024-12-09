import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarProductosComponent } from './components/productos/listar-productos/listar-productos.component';
import { CrearProductosComponent } from './components/productos/crear-productos/crear-productos.component';
import { CrearCategoriasComponent } from './components/categorias/crear-categorias/crear-categorias.component';
import { ListarCategoriasComponent } from './components/categorias/listar-categorias/listar-categorias.component';
import { PageProductosComponent } from './components/productos/page-productos/page-productos.component';
import { PageCategoriasComponent } from './components/categorias/page-categorias/page-categorias.component';
import { PageProductosCategoriaComponent } from './components/productos/page-productos-categoria/page-productos-categoria.component';
import { RegistrarEntradasComponent } from './components/productos/registrar-entradas/registrar-entradas.component';
import { FechaEntradasComponent } from './components/productos/fecha-entradas/fecha-entradas.component';
import { RegistrarSalidaComponent } from './components/productos/registrar-salida/registrar-salida.component';
import { FechaSalidasComponent } from './components/productos/fecha-salidas/fecha-salidas.component';
import { ActualizarProductoComponent } from './components/productos/actualizar-producto/actualizar-producto.component';
import { EliminarProductoComponent } from './components/productos/eliminar-producto/eliminar-producto.component';
import { ActualizarCategoriasComponent } from './components/categorias/actualizar-categorias/actualizar-categorias.component';
import { EliminarCategoriasComponent } from './components/categorias/eliminar-categorias/eliminar-categorias.component';

const routes: Routes = [
  { path: '', redirectTo: '/page-productos', pathMatch: 'full' }, // Redirige a listar-productos
  { path: 'listar-productos', component: ListarProductosComponent },
  { path: 'page-productos', component: PageProductosComponent },
  { path: 'page-categorias', component: PageCategoriasComponent },
  { path: 'listar-categorias', component: ListarCategoriasComponent },
  { path: 'crear-productos', component: CrearProductosComponent },
  { path: 'crear-categorias', component: CrearCategoriasComponent },
  { path: 'page-productos-categoria', component: PageProductosCategoriaComponent },
  { path: 'registrar-entrada', component: RegistrarEntradasComponent },
  { path: 'registrar-salida', component: RegistrarSalidaComponent },
  { path: 'fecha-entrada', component: FechaEntradasComponent },
  { path: 'fecha-salida', component: FechaSalidasComponent },
  { path: 'actualizar-producto', component: ActualizarProductoComponent },
  { path: 'eliminar-producto', component: EliminarProductoComponent },
  { path: 'actualizar-categoria', component: ActualizarCategoriasComponent },
  { path: 'eliminar-categoria', component: EliminarCategoriasComponent },
  { path: '**', redirectTo: '/listar-productos' } // Manejo de rutas desconocidas
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
