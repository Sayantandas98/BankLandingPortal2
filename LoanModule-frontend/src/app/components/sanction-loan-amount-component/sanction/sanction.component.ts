import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoanApplication } from 'src/app/Entities/LoanApplication';
import { LoanApplicationServiceService } from 'src/app/service/loan-application-service.service';

@Component({
  selector: 'app-sanction',
  templateUrl: './sanction.component.html',
  styleUrls: ['./sanction.component.css']
})
export class SanctionComponent implements OnInit {
  id!:string
  loanApplication!:LoanApplication
  constructor(private route: ActivatedRoute,private loanApplicationService:LoanApplicationServiceService,private router:Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.loanApplication=new LoanApplication()
    this.loanApplicationService.getLoanApplicationById(this.id).subscribe(data=>this.loanApplication=data)
  }

  sanction(){
    this.loanApplication.status="sanction";
    this.loanApplicationService.updateLoanApplication(this.id,this.loanApplication).subscribe(data=>console.log(data))
    console.log(this.id)
    this.loanApplication=new LoanApplication()
    setTimeout(() => {
      this.router.navigate(['/getallloanapplication']);
      alert("Loan Application sanctioned")
    }, 500);
  }

  reject(){
    this.loanApplication.status="rejected";
    this.loanApplicationService.updateLoanApplication(this.id,this.loanApplication).subscribe(data=>console.log(data))
    this.loanApplication=new LoanApplication()
    setTimeout(() => {
      this.router.navigate(['/getallloanapplication']);
      alert("Loan Application rejected")
    }, 500);
  }
}
