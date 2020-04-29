import { ToastrService } from 'ngx-toastr';
import { AddProductCategoryDTO } from './../../../../models/add-product-category-dto.model';
import { ProductCategoriesService } from '../../../../services/product-categories.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-categories',
  templateUrl: './product-categories.component.html',
  styleUrls: ['./product-categories.component.css']
})
export class ProductCategoriesComponent implements OnInit {

  categories = [];
  categoryName: string = '';

  constructor(private categoriesService: ProductCategoriesService,
              private toastr: ToastrService) { 
  }

  ngOnInit() {
    this.getAll();
  }

  private getAll(): void {
    this.categoriesService.getAll().subscribe(data => {
      this.categories = data;
    }, error => {
      this.toastr.error('There was an error while getting the product categories');
    });
  }

  onClickAdd(): void {
    const category: AddProductCategoryDTO = {
      name: this.categoryName
    };

    this.categoriesService.add(category).subscribe(data => {
      this.categories.push(data);
      this.categoryName = '';
      this.toastr.success('Category has been successfully added');
    }, error => {
      this.toastr.error(error.error.message);
    });
  }

  onClickDelete(id: number): void {
    this.categoriesService.delete(id).subscribe(data => {
      this.toastr.success('Category has been deleted');
      this.getAll();
    }, error => {
      this.toastr.error(error.error.message);
    });
  }
}
