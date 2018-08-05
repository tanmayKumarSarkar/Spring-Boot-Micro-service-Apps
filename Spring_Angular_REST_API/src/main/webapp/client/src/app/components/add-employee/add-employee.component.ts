import { Component, OnInit } from '@angular/core';
import { EmployeeServiceService } from '../../services/employee-service.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  constructor(private es: EmployeeServiceService) { }

  employee;
  name: String;
  dept: String;
  salary: Number;

  ngOnInit() {
  }

  onEmpSubmit() {

    this.employee = {
      name: this.name,
      depertment: this.dept,
      salary: this.salary
    }
    this.es.addEmployee(this.employee).subscribe(data => {

      this.es.getEmployees().subscribe(data => {
        this.es.employees = data;
      });

      this.name = null;
      this.dept = null;
      this.salary = null;
    });
  }

}
