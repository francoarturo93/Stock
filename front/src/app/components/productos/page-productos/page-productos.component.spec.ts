import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageProductosComponent } from './page-productos.component';

describe('PageProductosComponent', () => {
  let component: PageProductosComponent;
  let fixture: ComponentFixture<PageProductosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageProductosComponent]
    });
    fixture = TestBed.createComponent(PageProductosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
