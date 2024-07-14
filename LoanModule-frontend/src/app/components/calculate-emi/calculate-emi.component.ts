import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EmiClacResponse } from 'src/app/Entities/EmiClacResponse';
import { Loan } from 'src/app/Entities/Loan';
import { LoanApplicationServiceService } from 'src/app/service/loan-application-service.service';

@Component({
  selector: 'app-calculate-emi',
  templateUrl: './calculate-emi.component.html',
  styleUrls: ['./calculate-emi.component.css']
})
export class CalculateEmiComponent implements OnInit {
  title='ReactiveForms'
  emiform!:FormGroup;
  Loan:any=new Loan()
  emiClacResponse:any=new EmiClacResponse()
  constructor(private loanApplicationService:LoanApplicationServiceService) { }

  ngOnInit(): void {

    this.emiform=new FormGroup({
      principleAmount:new FormControl(null,Validators.compose(
        [
          Validators.required,
          Validators.max(999999999),
          Validators.min(10000)
        ]
      )),
      tenure:new FormControl(null,Validators.compose(
        [
          Validators.required,
          Validators.min(1),
          Validators.max(999)
        ]
      )),
      interestRate:new FormControl(null,Validators.compose(
        [
          Validators.required,
          Validators.min(1),
          Validators.max(9999)
        ]
      )),
      dueDate:new FormControl(null),
      emi:new FormControl(null),
      totalAmt:new FormControl(null)
    })



    this.emiform.patchValue({
      emi: 'abc',
      totalAmt: 'def'
   })
  }


  // onSubmit(){
  //   this.loanApplicationService.getEmi(this.Loan).subscribe(data=>{
  //     console.log(data)
  //   })
  // }

  onSubmit(data:any){
    this.Loan.dueDate=data.dueDate;
    this.Loan.loanTenureMonths=data.tenure
    this.Loan.monthlyinterestRate=data.interestRate
    this.Loan.principleAmount=data.principleAmount
    this.loanApplicationService.getEmi(this.Loan).subscribe(data=>{
          this.emiClacResponse=data
          console.log(this.emiClacResponse),
          (error:any)=>console.log(error)
    })
  }
}
