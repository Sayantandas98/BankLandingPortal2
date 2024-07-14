import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { SharedDataService } from 'src/app/service/shared-data.service';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent implements OnInit {

  username = ''
  password = ''
  invalidLogin = false

  constructor(private router: Router,private loginservice: AuthenticationService,private sharedMessage:SharedDataService) { }

  ngOnInit() {
    
  }

  checkLogin() {
    this.loginservice.authenticate(this.username, this.password).then(()=>{
      this.router.navigate(['home'])
      this.invalidLogin = false
      this.sharedMessage.raiseDataEmitter(true);
    }).catch(()=>{
      window.alert('wrong username or password');
      this.invalidLogin = true;
    })
    this.username = '';
    this.password = '';
  }
}
