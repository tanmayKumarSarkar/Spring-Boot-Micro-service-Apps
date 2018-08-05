import { Component, OnInit } from '@angular/core';
import { EmployeeServiceService } from '../../services/employee-service.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  constructor(private es: EmployeeServiceService) {
    this._serviceSubscription = this.es.valueChange.subscribe({
      next: (data: String) => {
        if (data === "editEmpUpdated") {
          this.employee = this.es.editEmpDetails;
          this.name = this.employee.name;
          this.dept = this.employee.depertment;
          this.salary = this.employee.salary;
        }
      }
    });
  }

  private _serviceSubscription;
  employee;
  name: String;
  dept: String;
  salary: Number;

  ngOnInit() {
    this.employee = this.es.editEmpDetails;
    this.name = this.employee.name;
    this.dept = this.employee.depertment;
    this.salary = this.employee.salary;
  }

  // ngAfterContentChecked() {
  //   this.employee = this.es.editEmpDetails;
  //   this.name = this.employee.name;
  //   this.dept = this.employee.depertment;
  //   this.salary = this.employee.salary;
  // }

  onUpdateEmpSubmit() {

    this.employee = {
      id: this.employee.id,
      name: this.name,
      depertment: this.dept,
      salary: this.salary
    }
    //console.log(this.name);
    this.es.updateEmployee(this.employee).subscribe(data => {

      this.es.getEmployees().subscribe(data => {
        this.es.employees = data;
      });
    });
  }

  addNewEmp() {
    this.es.isEditEmp = false;
    this.es.editEmpDetails = null;
  }

}
