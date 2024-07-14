import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoanApplication } from 'src/app/Entities/LoanApplication';
import { LoanApplicationServiceService } from 'src/app/service/loan-application-service.service';

@Component({
  selector: 'app-pull-new-monthly-loan-applications-component',
  templateUrl: './pull-new-monthly-loan-applications-component.component.html',
  styleUrls: ['./pull-new-monthly-loan-applications-component.component.css']
})
export class PullNewMonthlyLoanApplicationsComponentComponent implements OnInit{

  date=new Date
  str!:String
  loanApplication:LoanApplication[]=[];
  // size:number=this.loanApplication.length
  constructor(private route:ActivatedRoute,private loanApplicationService:LoanApplicationServiceService,private router:Router) { }

  ngOnInit(): void {
  }


  getLoanApplication(){
    this.loanApplicationService.getLoanApplicationsbyDate(this.date).subscribe(data=>{
      this.loanApplication=data;
    },error=>alert(error.error.message))
  }
}
