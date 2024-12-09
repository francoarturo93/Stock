import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageProductosCategoriaComponent } from './page-productos-categoria.component';

describe('PageProductosCategoriaComponent', () => {
  let component: PageProductosCategoriaComponent;
  let fixture: ComponentFixture<PageProductosCategoriaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageProductosCategoriaComponent]
    });
    fixture = TestBed.createComponent(PageProductosCategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
