import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { EmployeeServiceService } from '../../services/employee-service.service';

@Component({
  selector: 'app-all-employees',
  templateUrl: './all-employees.component.html',
  styleUrls: ['./all-employees.component.css']
})
export class AllEmployeesComponent implements OnInit {

  constructor(private es: EmployeeServiceService) { }

  employees;

  ngOnInit() {
    this.es.getEmployees().subscribe(data => {
      this.employees = this.es.employees = data;
    });
  }

  ngAfterContentChecked() {
    this.employees = this.es.employees
  }

  editEpmloyeeForm(emp) {
    this.es.isEditEmp = true;
    this.es.editEmpDetails = emp;
    this.es.triggerEvent("editEmpUpdated");
    //this.es.valueChange.emit({ employee: emp });
  }

  deleteEpmloyee(emp) {
    this.es.deleteEmployee(emp.id).subscribe(data => {
      //console.log(data);
      if (data.success) {
        this.es.getEmployees().subscribe(data => {
          this.es.employees = data;
        });
      }
    });
  }

}
