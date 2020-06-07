import { Observable } from 'rxjs';
import { ADD_RULE_TEMPLATE_URL } from './../../../config/api-paths';
import { AddRuleDTO } from './../../../models/add-rule.dto.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RuletemplateService {

  constructor(private http: HttpClient) { 
  }

  addRules(rules: AddRuleDTO[]): Observable<any> {
    return this.http.post(ADD_RULE_TEMPLATE_URL, rules);
  }
}
