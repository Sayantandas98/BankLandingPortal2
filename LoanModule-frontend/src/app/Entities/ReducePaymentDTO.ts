export class ReducePaymentDTO{
    loanappid!:string
    monthNo!:number
    installment!:DoubleRange
    interest!:DoubleRange
    poutStandingBeginOfMon!:DoubleRange
    prepayment!:DoubleRange
    poutStandingEndOfMon!:DoubleRange
}