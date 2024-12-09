import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FechaSalidasComponent } from './fecha-salidas.component';

describe('FechaSalidasComponent', () => {
  let component: FechaSalidasComponent;
  let fixture: ComponentFixture<FechaSalidasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FechaSalidasComponent]
    });
    fixture = TestBed.createComponent(FechaSalidasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
