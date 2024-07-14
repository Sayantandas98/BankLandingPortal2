import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReducePaymentDTO } from 'src/app/Entities/ReducePaymentDTO';
import { LoanApplicationServiceService } from 'src/app/service/loan-application-service.service';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit {
  paymentList!:ReducePaymentDTO[]
  id!:string
  constructor(private loanApplicationService:LoanApplicationServiceService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.loanApplicationService.getReducePayments(this.id).subscribe(data=>{
      this.paymentList=data
    })
  }

}
