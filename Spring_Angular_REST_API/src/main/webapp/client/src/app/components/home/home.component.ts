import { Component, OnInit } from '@angular/core';
import { EmployeeServiceService } from '../../services/employee-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private es: EmployeeServiceService) { }

  isEditEmp: Boolean;

  ngOnInit() {
    this.isEditEmp = this.es.isEditEmp;
  }

  ngAfterContentChecked() {
    this.isEditEmp = this.es.isEditEmp;
  }


}
