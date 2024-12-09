import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EliminarCategoriasComponent } from './eliminar-categorias.component';

describe('EliminarCategoriasComponent', () => {
  let component: EliminarCategoriasComponent;
  let fixture: ComponentFixture<EliminarCategoriasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EliminarCategoriasComponent]
    });
    fixture = TestBed.createComponent(EliminarCategoriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
