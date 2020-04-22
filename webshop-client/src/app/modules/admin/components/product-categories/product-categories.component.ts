import { AddProductCategoryDTO } from './../../../../models/add-product-category-dto.model';
import { ProductCategoriesService } from './../../services/product-categories.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-categories',
  templateUrl: './product-categories.component.html',
  styleUrls: ['./product-categories.component.css']
})
export class ProductCategoriesComponent implements OnInit {

  categories = [];
  categoryName: string = '';

  constructor(private categoriesService: ProductCategoriesService) { 
  }

  ngOnInit() {
    this.getAll();
  }

  private getAll(): void {
    this.categoriesService.getAll().subscribe(data => {
      this.categories = data;
    }, error => {
      // @TODO: Dodaj toster
      console.log(error);
    });
  }

  onClickAdd(): void {
    const category: AddProductCategoryDTO = {
      name: this.categoryName
    };

    this.categoriesService.add(category).subscribe(data => {
      this.categories.push(data);
      this.categoryName = '';
    }, error => {
      // @TODO: Dodati toster
      console.log(error);
    });
  }

  onClickDelete(id: number): void {
    this.categoriesService.delete(id).subscribe(data => {
      // @TODO: dodati toster
      this.getAll();
    }, error => {
      // @TODO: dodati toster
      console.log(error);
    });
  }
}
