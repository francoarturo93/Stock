import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarCategoriasComponent } from './actualizar-categorias.component';

describe('ActualizarCategoriasComponent', () => {
  let component: ActualizarCategoriasComponent;
  let fixture: ComponentFixture<ActualizarCategoriasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActualizarCategoriasComponent]
    });
    fixture = TestBed.createComponent(ActualizarCategoriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
