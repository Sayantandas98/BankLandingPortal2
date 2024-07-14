import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { SharedDataService } from 'src/app/service/shared-data.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  val!:string
  signInOut:boolean=false
  constructor(private router:Router,private sharedService:SharedDataService,private loginservice: AuthenticationService) { 
    if(sessionStorage.getItem('username')!==null){
      this.signInOut=true
    }
  }

  ngOnInit(): void {
    this.sharedService.dataEmitter.subscribe((val)=>{
      this.signInOut=val;
    })
  }

  onClick(){
    this.sharedService.raiseDataEmitter(false);
    this.loginservice.logOut()
    this.router.navigate(['login'])
  }
}
