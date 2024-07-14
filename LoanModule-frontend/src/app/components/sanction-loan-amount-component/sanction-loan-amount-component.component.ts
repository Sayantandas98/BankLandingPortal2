import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoanApplication } from 'src/app/Entities/LoanApplication';
import { LoanApplicationServiceService } from 'src/app/service/loan-application-service.service';

@Component({
  selector: 'app-sanction-loan-amount-component',
  templateUrl: './sanction-loan-amount-component.component.html',
  styleUrls: ['./sanction-loan-amount-component.component.css']
})
export class SanctionLoanAmountComponentComponent implements OnInit {
  loanApplication!:LoanApplication[]
  constructor(private loanApplicationService:LoanApplicationServiceService,private router:Router) { }

  ngOnInit(): void {
    this.ggg()
  }


  private ggg(){
    this.loanApplicationService.getAllLoanApplications().subscribe(data=>{
      this.loanApplication=data;
    })
  }

  view(id:String){
    this.router.navigate(['/getallloanapplication',id])
  }
}
