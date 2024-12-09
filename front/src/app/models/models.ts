export interface Producto {
  id?: number;
  nombre: string;
  precio: number;
  entradas: number;
  categoria: Categoria;
  salidas?: number;
  stock?: number;
}

export interface Categoria {
  id?: number;
  nombre?: string;
}

export interface CategoriaDto {
  id?: number;
  nombre?: string;
}

export interface ProductoEntradaDTO {
  productoId: number;
  entradas: number;
}

export interface ProductoSalidaDTO {
  productoId: number;
  salidas: number;
}

export interface MenuItem {
  id: number;
  name: string;
  route?: string;
  hasSubmenu: boolean;
  isSubmenuOpen?: boolean;
  submenuItems?: { name: string, route: string }[];
}
