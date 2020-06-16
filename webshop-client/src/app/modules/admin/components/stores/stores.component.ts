import { StoreService } from './../../../../services/store.service';
import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-stores',
  templateUrl: './stores.component.html',
  styleUrls: ['./stores.component.css']
})
export class StoresComponent implements OnInit {

  stores: [] = [];

  constructor(private storeService: StoreService,
              private toastr: ToastrService) { 
  }

  ngOnInit() {
    this.getStores();
  }

  private getStores() {
    this.storeService.getAll().subscribe(data => {
      this.stores = data;
    }, error => {
      this.toastr.error('There was an error while getting the stores.');
    });
  }

  onClickDelete(storeId: number) {
    this.storeService.delete(storeId).subscribe(data => {
      this.toastr.success('Store has been successfully deleted');
      this.getStores();
    }, error => {
      this.toastr.error(error.error.message);
    });
  }
}
