import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoanApplication } from '../Entities/LoanApplication';
import { ReducePaymentDTO } from '../Entities/ReducePaymentDTO';

@Injectable({
  providedIn: 'root'
})
export class LoanApplicationServiceService {
  private baseURL="http://localhost:8082/api/loanapps"
  constructor(private HttpClient:HttpClient) { }

  getLoanApplicationsbyDate(date:Date):Observable<LoanApplication[]>{
    return this.HttpClient.get<LoanApplication[]>(`${this.baseURL}/pull/${date}`)
  }
  getLoanApplicationById(id:string):Observable<LoanApplication>{
    return this.HttpClient.get<LoanApplication>(`${this.baseURL}/view/${id}`)
  }

  updateLoanApplication(id: string, loanApplication: LoanApplication): Observable<Object>{
    return this.HttpClient.put(`${this.baseURL}/update/${id}`, loanApplication);
  }
  getAllLoanApplications():Observable<LoanApplication[]>{
    return this.HttpClient.get<LoanApplication[]>(`${this.baseURL}/getall`)
  }

  getEmi(loan:any):Observable<object>{
    return this.HttpClient.post<any>(`${this.baseURL}/checkEmi`,loan);
  }

  getReducePayments(id:string):Observable<ReducePaymentDTO[]>{
    return this.HttpClient.get<ReducePaymentDTO[]>(`${this.baseURL}/sanctionAmount/${id}`)
  }
}
