import { AddRuleDTO } from './../../../../models/add-rule.dto.model';
import { ToastrService } from 'ngx-toastr';
import { RuletemplateService } from './../../services/ruletemplate.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-rule',
  templateUrl: './add-rule.component.html',
  styleUrls: ['./add-rule.component.css']
})
export class AddRuleComponent implements OnInit {

  rules: AddRuleDTO[] = [];
  addForm: FormGroup;

  constructor(private ruleTemplateService: RuletemplateService,
              private toastr: ToastrService,
              private fb: FormBuilder) { 
    this.createForm();
  }

  ngOnInit() {
  }

  private createForm(): void {
    this.addForm = this.fb.group({
      buyerCategory: ['', Validators.required],
      newCategory: ['', Validators.required],
      ordersSize: ['', Validators.required],
      moneySpent: ['', Validators.required]
    });
  }

  addRule() {
    const rule: AddRuleDTO = {
      buyerCategory: this.addForm.value.buyerCategory,
      newCategory: this.addForm.value.newCategory,
      ordersSize: this.addForm.value.ordersSize,
      moneySpent: this.addForm.value.moneySpent
    };

    this.rules.push(rule);
    this.addForm.reset();
  }

  submitRules() {
    this.ruleTemplateService.addRules(this.rules).subscribe(data => {
      this.toastr.success('New rules have been successfully created');
    }, error => {
      this.toastr.error(error.error.message);
    });
  }

}
