import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { CrearProductosComponent } from './components/productos/crear-productos/crear-productos.component';
import { ListarProductosComponent } from './components/productos/listar-productos/listar-productos.component';
import { CrearCategoriasComponent } from './components/categorias/crear-categorias/crear-categorias.component';
import { ListarCategoriasComponent } from './components/categorias/listar-categorias/listar-categorias.component';
import { CommonModule } from '@angular/common';
import { PageProductosComponent } from './components/productos/page-productos/page-productos.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    CrearProductosComponent,
    ListarProductosComponent,
    CrearCategoriasComponent,
    ListarCategoriasComponent,
    PageProductosComponent,
    PageCategoriasComponent,
    PageProductosCategoriaComponent,
    RegistrarEntradasComponent,
    FechaEntradasComponent,
    RegistrarSalidaComponent,
    FechaSalidasComponent,
    ActualizarProductoComponent,
    EliminarProductoComponent,
    ActualizarCategoriasComponent,
    EliminarCategoriasComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
