import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageCategoriasComponent } from './page-categorias.component';

describe('PageCategoriasComponent', () => {
  let component: PageCategoriasComponent;
  let fixture: ComponentFixture<PageCategoriasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageCategoriasComponent]
    });
    fixture = TestBed.createComponent(PageCategoriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
