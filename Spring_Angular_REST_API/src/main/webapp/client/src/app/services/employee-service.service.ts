import { Injectable, EventEmitter } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import 'rxjs/Rx';

@Injectable()
export class EmployeeServiceService {

  constructor(private http: Http) { }

  employees;
  isEditEmp: Boolean = false;
  editEmpDetails;
  localhost: String = "http://localhost:8060/api";

  valueChange: EventEmitter<String> = new EventEmitter<String>();

  triggerEvent(message) {
    this.valueChange.emit(message);
  }

  getEmployees() {
    return this.http.get(`${this.localhost}/employees`)
      .map(res => res.json());
  }

  addEmployee(emp) {
    let headers = new Headers();
    headers.append('Content-type', 'application/json');
    return this.http.post(`${this.localhost}/employees`, emp, { headers: headers })
      .map(res => res.json());
  }

  updateEmployee(emp) {
    let headers = new Headers();
    headers.append('Content-type', 'application/json');
    return this.http.put(`${this.localhost}/employee`, emp, { headers: headers })
      .map(res => res.json());
  }

  deleteEmployee(id) {
    return this.http.delete(`${this.localhost}/employee/${id}`)
      .map(res => res.json());
  }

}
