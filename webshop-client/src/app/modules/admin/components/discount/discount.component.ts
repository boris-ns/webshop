import { ToastrService } from 'ngx-toastr';
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

  constructor(private discountService: DiscountService,
              private toastr: ToastrService) { 
  }

  ngOnInit() {
    this.getSeasonDiscounts();
    this.getCategoryDiscounts();
  }

  private getSeasonDiscounts(): void {
    this.discountService.getAllSeasonDiscounts().subscribe(data => {
      this.seasonDiscounts = data;
    }, error => {
      this.toastr.error('There was an error while getting the season discounts.');
    });
  }

  private getCategoryDiscounts(): void {
    this.discountService.getAllCategoryDiscounts().subscribe(data => {
      this.categoryDiscounts = data;
    }, error => {
      this.toastr.error('There was an error while getting the category discounts.');
    });
  }

  onClickDeleteSeasonDiscount(id: number): void {
    this.discountService.deleteSeasonDiscount(id).subscribe(data => {
      this.toastr.success('Season discount has been deleted');
      this.getSeasonDiscounts();
    }, error => {
      this.toastr.error(error.error.message);
    });
  }

  onClickDeleteCategoryDiscount(id: number): void {
    this.discountService.deleteCategoryDiscount(id).subscribe(data => {
      this.toastr.success('Category discount has been deleted');
      this.getCategoryDiscounts();
    }, error => {
      this.toastr.error(error.error.message);
    });
  }

  seasonDiscountAdded(added: boolean): void {
    this.toastr.success('Season discount has been successfully added');
    this.getSeasonDiscounts();
  }

  categoryDiscountAdded(added: boolean): void {
    this.toastr.success('Category discount has been successfully added');
    this.getCategoryDiscounts();
  }

  onClickAddSeasonDiscount(): void {
    this.showAddSeasonDiscountDialog = true;
  }

  onClickCloseAddSeasonDiscountDialog(): void {
    this.showAddSeasonDiscountDialog = false;
  }

  onClickAddCategoryDiscount(): void {
    this.showAddCategoryDiscountDialog = true;
  }

  onClickCloseAddCategoryDiscountDialog(): void {
    this.showAddCategoryDiscountDialog = false;
  }
}
