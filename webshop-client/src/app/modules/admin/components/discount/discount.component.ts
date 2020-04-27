import { DiscountService } from './../../services/discount.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-discount',
  templateUrl: './discount.component.html',
  styleUrls: ['./discount.component.css']
})
export class DiscountComponent implements OnInit {

  seasonDiscounts: [] = [];
  categoryDiscounts: [] = [];

  showAddSeasonDiscountDialog: boolean = false;
  showAddCategoryDiscountDialog: boolean = false;

  constructor(private discountService: DiscountService) { 
  }

  ngOnInit() {
    this.getSeasonDiscounts();
    this.getCategoryDiscounts();
  }

  private getSeasonDiscounts(): void {
    this.discountService.getAllSeasonDiscounts().subscribe(data => {
      this.seasonDiscounts = data;
    }, error => {
      // @TODO: dodati toster
      console.log(error);
    });
  }

  private getCategoryDiscounts(): void {
    this.discountService.getAllCategoryDiscounts().subscribe(data => {
      this.categoryDiscounts = data;
    }, error => {
      // @TODO: dodati toster
      console.log(error);
    });
  }

  onClickDeleteSeasonDiscount(id: number): void {
    this.discountService.deleteSeasonDiscount(id).subscribe(data => {
      // @TODO: dodati toster
      this.getSeasonDiscounts();
    }, error => {
      // @TODO: dodati toster
    });
  }

  onClickDeleteCategoryDiscount(id: number): void {
    this.discountService.deleteCategoryDiscount(id).subscribe(data => {
      this.getCategoryDiscounts();
      // @TODO: dodati toster
    }, error => {
      // @TODO: dodati toster
    });
  }

  seasonDiscountAdded(added: boolean): void {
    this.getSeasonDiscounts();
  }

  onClickAddSeasonDiscount(): void {
    this.showAddSeasonDiscountDialog = true;
  }

  onClickCloseAddSeasonDiscountDialog(): void {
    this.showAddSeasonDiscountDialog = false;
  }
}
