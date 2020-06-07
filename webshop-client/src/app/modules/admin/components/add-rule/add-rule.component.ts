import { AddRuleDTO } from './../../../../models/add-rule.dto.model';
import { ToastrService } from 'ngx-toastr';
import { RuletemplateService } from './../../services/ruletemplate.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-rule',
  templateUrl: './add-rule.component.html',
  styleUrls: ['./add-rule.component.css']
})
export class AddRuleComponent implements OnInit {

  buyerCategory: string =  '';
  newCategory: string = '';
  ordersSize: number = 0;
  moneySpent: number = 0.0;

  rules: AddRuleDTO[] = [];

  constructor(private ruleTemplateService: RuletemplateService,
              private toastr: ToastrService) { 
  }

  ngOnInit() {
  }

  addRule() {

  }

  submitRules() {
    this.ruleTemplateService.addRules(this.rules).subscribe(data => {
      this.toastr.success('New rules have been successfully created');
    }, error => {
      this.toastr.error(error.error.message);
    });
  }

}
