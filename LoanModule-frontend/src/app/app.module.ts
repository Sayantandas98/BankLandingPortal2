import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SanctionLoanAmountComponentComponent } from './components/sanction-loan-amount-component/sanction-loan-amount-component.component';
import { UodateLoanApplicationComponentComponent } from './components/uodate-loan-application-component/uodate-loan-application-component.component';
import { PullNewMonthlyLoanApplicationsComponentComponent } from './components/pull-new-monthly-loan-applications-component/pull-new-monthly-loan-applications-component.component';
import { ViewSpecificLoanApplicationsComponentComponent } from './components/view-specific-loan-applications-component/view-specific-loan-applications-component.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavBarComponent } from './navbar/nav-bar/nav-bar.component';
import {HttpClientModule} from '@angular/common/http'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SanctionComponent } from './components/sanction-loan-amount-component/sanction/sanction.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { LoanApplicationByIdComponent } from './components/loan-application-by-id/loan-application-by-id.component';
import { ErrorpageComponent } from './components/errorpage/errorpage.component';
import { CalculateEmiComponent } from './components/calculate-emi/calculate-emi.component';
import { SanctionsListComponent } from './components/sanctions-list/sanctions-list.component';
import { PaymentsComponent } from './components/sanctions-list/payments/payments.component';
import { LogoutComponent } from './components/logout/logout.component';
import { LoginComponentComponent } from './components/login-component/login-component.component';
import {MatCardModule} from '@angular/material/card';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
    AppComponent,
    SanctionLoanAmountComponentComponent,
    UodateLoanApplicationComponentComponent,
    PullNewMonthlyLoanApplicationsComponentComponent,
    ViewSpecificLoanApplicationsComponentComponent,
    NavBarComponent,
    SanctionComponent,
    FooterComponent,
    HomeComponent,
    LoanApplicationByIdComponent,
    ErrorpageComponent,
    CalculateEmiComponent,
    SanctionsListComponent,
    PaymentsComponent,
    LogoutComponent,
    LoginComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    FontAwesomeModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
