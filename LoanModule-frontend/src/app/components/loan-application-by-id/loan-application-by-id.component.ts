import { Component, OnInit } from '@angular/core';
import { LoanApplication } from 'src/app/Entities/LoanApplication';
import { LoanApplicationServiceService } from 'src/app/service/loan-application-service.service';

@Component({
  selector: 'app-loan-application-by-id',
  templateUrl: './loan-application-by-id.component.html',
  styleUrls: ['./loan-application-by-id.component.css']
})
export class LoanApplicationByIdComponent implements OnInit {

  loanApplications:LoanApplication[]=[]
  loanApplication:LoanApplication[]=[]
  search:string=""
  noData:boolean=false
  constructor(private loanApplicationService:LoanApplicationServiceService) { }

  ngOnInit(): void {
    this.loanApplicationService.getAllLoanApplications().subscribe((data)=>{
      this.loanApplications=data
      this.loanApplication=data
    })
  }

  change(){
    if(this.search==''){
      this.loanApplication=this.loanApplications
      this.noData=false
    }
    else if(this.loanApplications.filter(e=>e.loanAppId.includes(this.search)).length===0){
      this.loanApplication=[]
      this.noData=true
    }
    else{
      this.loanApplication=this.loanApplications.filter(e=>e.loanAppId.includes(this.search))
      this.noData=false
    }
  }


}
