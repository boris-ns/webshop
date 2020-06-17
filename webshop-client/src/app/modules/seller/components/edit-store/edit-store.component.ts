import { EditStoreDTO } from './../../../../models/edit-store.dto.model';
import { StoreDTO } from './../../../../models/store.dto.model';
import { ToastrService } from 'ngx-toastr';
import { StoreService } from './../../../../services/store.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-store',
  templateUrl: './edit-store.component.html',
  styleUrls: ['./edit-store.component.css']
})
export class EditStoreComponent implements OnInit {

  store: StoreDTO;
  editStore: EditStoreDTO = {};

  constructor(private storeService: StoreService,
              private toastr: ToastrService) { 
  }

  ngOnInit() {
    this.getMyStore();
  }

  private getMyStore(): void {
    this.storeService.getMyStore().subscribe((data: StoreDTO) => {
      this.store = data;
      this.editStore.name = data.name;
      this.editStore.frequentBuyerDiscount = data.frequentBuyerDiscount * 100;
    }, error => {
      this.toastr.error('There was an error while getting the data about your store');
    });
  }  

  onClickEdit(): void {
    this.storeService.edit(this.editStore).subscribe((data: StoreDTO) => {
      this.store = data;
      this.editStore.name = data.name;
      this.editStore.frequentBuyerDiscount = data.frequentBuyerDiscount * 100;
      this.toastr.success('Store has been successfully updated');  
    }, error => {
      this.toastr.error(error.error.message);
    });    
  }

  onClickReset(): void {
    this.editStore.name = this.store.name;
    this.editStore.frequentBuyerDiscount = this.store.frequentBuyerDiscount * 100;
  }
}
