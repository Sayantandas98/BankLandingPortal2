import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoanApplication } from 'src/app/Entities/LoanApplication';
import { LoanApplicationServiceService } from 'src/app/service/loan-application-service.service';

@Component({
  selector: 'app-view-specific-loan-applications-component',
  templateUrl: './view-specific-loan-applications-component.component.html',
  styleUrls: ['./view-specific-loan-applications-component.component.css']
})
export class ViewSpecificLoanApplicationsComponentComponent implements OnInit {
  date!:Date
  id!:string
  loanApplication!:LoanApplication;
  constructor(private route:ActivatedRoute,private loanApplicationService:LoanApplicationServiceService) { }

  ngOnInit(): void {
    this.loanApplication=new LoanApplication()
  }


  ggg(){
    this.loanApplicationService.getLoanApplicationsbyDate(this.date).subscribe(data=>{
      console.log(data)
    })
  }
}
