import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DiscountService } from './../../services/discount.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import AddSeasonDiscountDTO from 'src/app/models/add-season-discount-dto.model';

@Component({
  selector: 'app-add-season-discount',
  templateUrl: './add-season-discount.component.html',
  styleUrls: ['./add-season-discount.component.css']
})
export class AddSeasonDiscountComponent implements OnInit {

  @Output() 
  discountAdded = new EventEmitter<boolean>();

  addForm: FormGroup;

  constructor(private discountService: DiscountService,
              private fb: FormBuilder) { 
    this.createForm();
  }

  ngOnInit() {
  }

  private createForm(): void {
    this.addForm = this.fb.group({
      name: ['', Validators.required],
      dateFrom: ['', Validators.required],
      dateTo: ['', Validators.required],
      discount: ['', Validators.required]
    });
  }

  onSubmit(): void {
    const startDate = this.addForm.value.dateFrom.split('-');
    const endDate = this.addForm.value.dateTo.split('-');

    const discount: AddSeasonDiscountDTO = {
      name: this.addForm.value.name,
      from: [
        startDate[2], startDate[1], startDate[0]
      ],
      to: [
        endDate[2], endDate[1], endDate[0]
      ],
      discount: this.addForm.value.discount
    };

    this.discountService.addSeasonDiscount(discount).subscribe(data => {
      // @TODO: dodaj toster
      this.discountAdded.emit(true);
    }, error => {
      console.log(error);
      // @TODO: dodaj toster
    });
  }
}
