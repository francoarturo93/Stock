import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Categoria, CategoriaDto, Producto, ProductoEntradaDTO, ProductoSalidaDTO } from '../models/models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InventarioService {

  private apiUrl = "http://localhost:8080/api/producto";
  private apiUrlStock = "http://localhost:8080/api/stock";
  private apiUrlCategoria = "http://localhost:8080/api/categoria";

  constructor(private http: HttpClient) { }

  /* Producto */
  //Método para crear un producto en el backend */
  crearProducto(producto: Producto): Observable<Producto> {
    // const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Producto>(`${this.apiUrl}/crear-productos`, producto);
  }
  //Metodo para obtener una lista de productos */
  listarProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.apiUrl}/lista-productos`)
  }
  /* Page Producto*/
  pageProductos(page: number, size: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/pageproductos?page=${page}&size=${size}`);
  }
  // Page de productos por categori<
  pageProductosPorCategoria(categoriaId: number ,page: number, size: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/productos-por-categoria?categoriaId=${categoriaId}&page=${page}&size=${size}`);
  }
  //Registrar Entradas
  agregarEntradas(productoEntrada: ProductoEntradaDTO): Observable<ProductoEntradaDTO>{
    return this.http.post<ProductoEntradaDTO>(`${this.apiUrlStock}/entradas`, productoEntrada);
  }
  //buscar por fechas
  buscarEntradasPorFecha(fecha: string, page: number, size: number): Observable<any> {
    return this.http.get(`${this.apiUrlStock}/buscarEntradasPorFecha?fecha=${fecha}&page=${page}&size=${size}`);
  }
  //Registrar Salidas
  registrarSaida(productoSalida: ProductoSalidaDTO): Observable<ProductoSalidaDTO>{
    return this.http.post<ProductoSalidaDTO>(`${this.apiUrlStock}/salidas`, productoSalida);
  }
  //buscar por fechas
  buscarSalidasPorFecha(fecha: string, page: number, size: number): Observable<any> {
    return this.http.get(`${this.apiUrlStock}/buscarSalidasPorFecha?fecha=${fecha}&page=${page}&size=${size}`);
  }
  //actualizar
  actualizarProducto(id: number, producto: Producto): Observable<Producto> {
    return this.http.put<Producto>(`${this.apiUrl}/actualizar/${id}`, producto);
  }
  //eliminar
  eliminarProducto(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  /************************************************************ */
  /* Categorias */
  //Método para crear categorías desde el backend */
  crearCategoria(categoria: Categoria): Observable<Categoria> {
    // const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Categoria>(`${this.apiUrlCategoria}/crear-categorias`, categoria);
  }
  //actualizar
  actualizarCategoria(id: number, categoria: Categoria): Observable<Categoria> {
    return this.http.put<Producto>(`${this.apiUrlCategoria}/actualizar/${id}`, categoria);
  }
  //eliminar
  eliminarCategoria(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrlCategoria}/${id}`);
  }
  //Método para obtener las categorías desde el backend */
  listarCategorias(): Observable<CategoriaDto[]> {
    return this.http.get<Categoria[]>(`${this.apiUrlCategoria}/lista-categorias`);
  }
  listCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${this.apiUrlCategoria}/list-categorias`);
  }
  /* Page Categorias*/
  pageCategorias(page: number, size: number): Observable<any> {
    return this.http.get(`${this.apiUrlCategoria}/pagecategorias?page=${page}&size=${size}`);
  }
  /* Importar Excel */
  importarProductos(file: File): Observable<string> {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post<string>(`${this.apiUrlStock}/importar`, formData, {
      headers: new HttpHeaders().set('Accept', 'application/json'),
      responseType: 'text' as 'json',
    });
  }
  /* Exportar Excel */
  exportarProductos(): Observable<Blob>{
    return this.http.get(`${this.apiUrlStock}/exportar`, { responseType: 'blob' });
  }
  //exportar entradas
  exportarEntradas(): Observable<Blob>{
    return this.http.get(`${this.apiUrlStock}/exportar-entradas`, { responseType: 'blob' });
  }
  //exportar salidas
  exportarSalidas(): Observable<Blob>{
    return this.http.get(`${this.apiUrlStock}/exportar-salidas`, { responseType: 'blob' });
  }

}
