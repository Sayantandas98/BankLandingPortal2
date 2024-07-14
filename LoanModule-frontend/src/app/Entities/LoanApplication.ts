import { CreditRisk } from "./CreditRisk";

export class LoanApplication{
    loanAppId!:String;
    custId!:String;
    loanAmt!:number
    noOfYears!:number;
    purpose!:String;
    appStatus!:String;
    typeOfLoan!:String;
    loanAppDate!:Date;
    status!:String;
    creditRisk!:CreditRisk
}