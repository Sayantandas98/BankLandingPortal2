


insert into Loan_Application(loan_appid,cust_id,loan_amt,no_of_years,purpose,app_status,type_of_loan,loan_app_date,status)
 values('L100','CRL100',30000,3,'Use','accepted','Personal Loan','2023-10-21','pending');
insert into Loan_Application values('L101','CRL101',10000,9,'Use','rejected','Home Loan','2023-09-01','pending');
insert into Loan_Application values('L102','CRL102',20890,8,'Use','accepted','Car Loan','2022-06-24','pending');
insert into Loan_Application values('L103','CRL103',287590,8,'Use','accepted','Car Loan','2022-06-24','pending');
insert into Loan_Application values('L104','CRL104',200560,5,'Use','accepted','Car Loan','2022-06-24','pending');
insert into Loan_Application values('L105','CRL105',500000,2,'Use','rejected','Auto Loan','2021-10-21','pending');
insert into Loan_Application values('L106','CRL106',2000000,9,'Use','rejected','Home Loan','2021-03-15','pending');
insert into Loan_Application values('L202','CRL202',20976,10,'Use','accepted','Car Loan','2021-10-21','pending');
insert into Loan_Application values('L240','CRL240',207647,8,'Use','accepted','Home Loan','2021-07-19','pending');
insert into Loan_Application values('L109','CRL109',20000,20,'Use','accepted','Car Loan','2021-02-16','pending');
 
 insert into Credit_Risk values('CRL100','L100',650,67987,'pass');
 insert into Credit_Risk values('CRL101','L101',750,67888,'fail');
 insert into Credit_Risk values('CRL102','L102',750,6754,'pass');
 insert into Credit_Risk values('CRL103','L103',750,6756,'fail');
 insert into Credit_Risk values('CRL104','L104',759,7688,'pass');
 insert into Credit_Risk values('CRL105','L105',767,37888,'fail');
 insert into Credit_Risk values('CRL106','L106',750,67888,'fail');
 insert into Credit_Risk values('CRL202','L202',780,7888,'pass');
 insert into Credit_Risk values('CRL240','L240',750,87888,'pass');
 insert into Credit_Risk values('CRL109','L109',800,9888,'fail');
 
 
 
insert into users values('sayan','sayan123','admin',false);