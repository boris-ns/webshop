import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCategoryDiscountComponent } from './add-category-discount.component';

describe('AddCategoryDiscountComponent', () => {
  let component: AddCategoryDiscountComponent;
  let fixture: ComponentFixture<AddCategoryDiscountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCategoryDiscountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCategoryDiscountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
