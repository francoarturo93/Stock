import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FechaEntradasComponent } from './fecha-entradas.component';

describe('FechaEntradasComponent', () => {
  let component: FechaEntradasComponent;
  let fixture: ComponentFixture<FechaEntradasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FechaEntradasComponent]
    });
    fixture = TestBed.createComponent(FechaEntradasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
