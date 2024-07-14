import { Component, OnInit } from '@angular/core';
import { CreditRisk } from 'src/app/Entities/CreditRisk';
import { LoanApplication } from 'src/app/Entities/LoanApplication';
import { LoanApplicationServiceService } from 'src/app/service/loan-application-service.service';

@Component({
  selector: 'app-uodate-loan-application-component',
  templateUrl: './uodate-loan-application-component.component.html',
  styleUrls: ['./uodate-loan-application-component.component.css']
})
export class UodateLoanApplicationComponentComponent implements OnInit {
  fetchData!:string
  loanApplication:LoanApplication=new LoanApplication()
  creditScore!:number
  basicCheck!:string

  constructor(private loanApplicationService:LoanApplicationServiceService) { }

  ngOnInit(): void {

  }

  fetch(){
    this.loanApplicationService.getLoanApplicationById(this.fetchData).subscribe(data=>{this.loanApplication=data;
    this.creditScore=data.creditRisk.creditScore;this.basicCheck=data.creditRisk.basicCheck},
      (error)=>alert(error.error.message))
  }

  onSubmit(){
    this.loanApplicationService.updateLoanApplication(this.fetchData,this.loanApplication).subscribe(data=>{
      alert("Loan application updated")
    })
    this.loanApplication=new LoanApplication()
    this.fetchData="";
    this.creditScore=0
    this.basicCheck=''
  }

}
