import { ProductCategoriesService } from './../../../../services/product-categories.service';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { DiscountService } from './../../services/discount.service';
import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import AddCategoryDiscountDTO from 'src/app/models/add-category-discount-dto.model';

@Component({
  selector: 'app-add-category-discount',
  templateUrl: './add-category-discount.component.html',
  styleUrls: ['./add-category-discount.component.css']
})
export class AddCategoryDiscountComponent implements OnInit {

  @Output() 
  discountAdded = new EventEmitter<boolean>();
  
  addForm: FormGroup;
  categories: [] = [];

  constructor(private discountService: DiscountService,
              private categoriesService: ProductCategoriesService,
              private fb: FormBuilder) { 
    this.createForm();
  }

  ngOnInit() {
    this.getCategories();
  }

  private createForm(): void {
    this.addForm = this.fb.group({
      categoryId: ['', Validators.required],
      dateFrom: ['', Validators.required],
      dateTo: ['', Validators.required]
    });
  }

  private getCategories(): void {
    this.categoriesService.getAll().subscribe(data => {
      this.categories = data;
    }, error => {
      console.log(error);
      // @TODO: dodaj toster
    });
  }

  onSubmit(): void {
    const startDate = this.addForm.value.dateFrom.split('-');
    const endDate = this.addForm.value.dateTo.split('-');

    const discount: AddCategoryDiscountDTO = {
      categoryId: this.addForm.value.categoryId,
      from: [
        startDate[2], startDate[1], startDate[0]
      ],
      to: [
        endDate[2], endDate[1], endDate[0]
      ]
    };

    this.discountService.addCategoryDiscount(discount).subscribe(data => {
      // @TODO: dodaj toster
      this.discountAdded.emit(true);
    }, error => {
      console.log(error);
      // @TODO: dodaj toster
    });
  }
}
