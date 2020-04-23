import { StoresService } from './../../services/stores.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-stores',
  templateUrl: './stores.component.html',
  styleUrls: ['./stores.component.css']
})
export class StoresComponent implements OnInit {

  stores: [] = [];

  constructor(private storesService: StoresService) { 
  }

  ngOnInit() {
    this.getStores();
  }

  private getStores() {
    this.storesService.getAll().subscribe(data => {
      this.stores = data;
    }, error => {
      console.log(error);
      // @TODO: dodati toster
    });
  }

  onClickDelete(storeId: number) {
    // @TODO: Implementirati
    console.log("DELETE", storeId);
  }
}
