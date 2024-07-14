import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoanApplication } from 'src/app/Entities/LoanApplication';
import { LoanApplicationServiceService } from 'src/app/service/loan-application-service.service';

@Component({
  selector: 'app-sanctions-list',
  templateUrl: './sanctions-list.component.html',
  styleUrls: ['./sanctions-list.component.css']
})
export class SanctionsListComponent implements OnInit {

loanApplication!:LoanApplication[]

  constructor(private loanApplicationService:LoanApplicationServiceService,private router:Router) { }

  ngOnInit(): void {
    this.loanApplicationService.getAllLoanApplications().subscribe(data=>this.loanApplication=data.filter(e=>e.status.toLocaleLowerCase()==='sanction'))
  }

  view(id:String){
    this.router.navigate(['/getAllSanctionLoanApplications',id])
  }

}
