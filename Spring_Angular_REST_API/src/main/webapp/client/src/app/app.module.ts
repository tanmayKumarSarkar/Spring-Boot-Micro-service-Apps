import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';


import { AppComponent } from './app.component';
import { AddEmployeeComponent } from './components/add-employee/add-employee.component';
import { ViewEmployeeComponent } from './components/view-employee/view-employee.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';
import { HomeComponent } from './components/home/home.component';
import { AllEmployeesComponent } from './components/all-employees/all-employees.component';

import { EmployeeServiceService } from './services/employee-service.service';


@NgModule({
  declarations: [
    AppComponent,
    AddEmployeeComponent,
    ViewEmployeeComponent,
    UpdateEmployeeComponent,
    HomeComponent,
    AllEmployeesComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule
  ],
  providers: [
    EmployeeServiceService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
