import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UodateLoanApplicationComponentComponent } from './components/uodate-loan-application-component/uodate-loan-application-component.component';
import { ViewSpecificLoanApplicationsComponentComponent } from './components/view-specific-loan-applications-component/view-specific-loan-applications-component.component';
import { PullNewMonthlyLoanApplicationsComponentComponent } from './components/pull-new-monthly-loan-applications-component/pull-new-monthly-loan-applications-component.component';
import { SanctionLoanAmountComponentComponent } from './components/sanction-loan-amount-component/sanction-loan-amount-component.component';
import { SanctionComponent } from './components/sanction-loan-amount-component/sanction/sanction.component';
import { HomeComponent } from './home/home.component';
import { LoanApplicationByIdComponent } from './components/loan-application-by-id/loan-application-by-id.component';
import { ErrorpageComponent } from './components/errorpage/errorpage.component';
import { CalculateEmiComponent } from './components/calculate-emi/calculate-emi.component';
import { SanctionsListComponent } from './components/sanctions-list/sanctions-list.component';
import { PaymentsComponent } from './components/sanctions-list/payments/payments.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AuthGaurdAdminService } from './service/auth-guardadmin.service';
import { LoginComponentComponent } from './components/login-component/login-component.component';

const routes: Routes = [
  {path:'',redirectTo:'home',pathMatch:'full'},
  {path:'home',component:HomeComponent},
  {path:'LoanApplicationById',component:LoanApplicationByIdComponent,canActivate:[AuthGaurdAdminService]},
  {path:'pull',component:PullNewMonthlyLoanApplicationsComponentComponent,canActivate:[AuthGaurdAdminService]},
  {path:'update',component:UodateLoanApplicationComponentComponent,canActivate:[AuthGaurdAdminService]},
  {path:'getallloanapplication',component:SanctionLoanAmountComponentComponent,canActivate:[AuthGaurdAdminService]},
  {path:'getallloanapplication/:id',component:SanctionComponent,canActivate:[AuthGaurdAdminService]},
  {path:'emi',component:CalculateEmiComponent,canActivate:[AuthGaurdAdminService]},
  {path:'getAllSanctionLoanApplications',component:SanctionsListComponent,canActivate:[AuthGaurdAdminService]},
  {path:'getAllSanctionLoanApplications/:id',component:PaymentsComponent,canActivate:[AuthGaurdAdminService]},
//   {path:'getbydate',component:UodateLoanApplicationComponentComponent},
//   {path:'pull1/:date',component:ViewSpecificLoanApplicationsComponentComponent},
//   {path:'view',component:SanctionLoanAmountComponentComponent},
{ path: 'login', component: LoginComponentComponent },
{ path: 'logout', component: LogoutComponent,canActivate:[AuthGaurdAdminService] },
  {path:'**',component:ErrorpageComponent,canActivate:[AuthGaurdAdminService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
