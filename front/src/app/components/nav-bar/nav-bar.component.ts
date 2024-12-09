import { Component } from '@angular/core';
import { MenuItem } from 'src/app/models/models';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent{

  menuItems = [
    {
      id: 1,
      name: 'Inicio',
      route: '/',
      hasSubmenu: false
    },
    {
      id: 2,
      name: 'Productos',
      hasSubmenu: true,
      isSubmenuOpen: false,

      submenuItems: [
        { name: 'Crear Productos', route: '/crear-productos' },
        { name: 'Buscar Productos por Categoria', route: '/page-productos-categoria' },
        { name: 'Actualizar Producto', route: '/actualizar-producto' },
        { name: 'Eliminar Producto', route: '/eliminar-producto' }
      ]
    },
    {
      id: 2,
      name: 'Categorias',
      hasSubmenu: true,
      isSubmenuOpen: false,

      submenuItems: [
        { name: 'Crear Categorias', route: '/crear-categorias' },
        { name: 'Page Categorias', route: '/page-categorias' },
        { name: 'Actualizar Categorias', route: '/actualizar-categoria' },
        { name: 'Eliminar Categorias', route: '/eliminar-categoria' }
      ]
    },
    {
      id: 3,
      name: 'Entradas',
      hasSubmenu: true,
      isSubmenuOpen: false,

      submenuItems: [
        { name: 'Registrar Entrada', route: '/registrar-entrada' },
        { name: 'Buscar por Fecha', route: '/fecha-entrada' }
      ]
    },
    {
      id: 3,
      name: 'Salidas',
      hasSubmenu: true,
      isSubmenuOpen: false,

      submenuItems: [
        { name: 'Registrar Salida', route: '/registrar-salida' },
        { name: 'Buscar por Fecha', route: '/fecha-salida' }
      ]
    }
  ];

  isMenuOpen = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
  toggleSubmenu(item: MenuItem) {
    item.isSubmenuOpen = !item.isSubmenuOpen;
  }
}
/*
*/
