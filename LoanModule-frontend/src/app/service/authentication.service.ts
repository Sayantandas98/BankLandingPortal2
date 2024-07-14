import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Banker } from '../Entities/Banker';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http:HttpClient) { }
  authenticateURL:string="http://localhost:8082/authenticate/users";
  user!: Banker
  authenticated!:boolean
  users!: Banker[]
  userBackend!:Banker

  
  authenticate(username: any, password: any) {
    this.user=new Banker();
    this.user.userName=username;
    this.user.password=password;
    let prm = new Promise<boolean>((resolve, reject) => {
      this.http.post<Banker>(this.authenticateURL, this.user)
        .subscribe((data) => {
          this.userBackend = new Banker();
          this.userBackend.userName = data.userName;
          this.userBackend.password = data.password;
          this.userBackend.role=data.role;
          if (this.user.userName === this.userBackend.userName && this.user.password == this.userBackend.password) {
            this.authenticated = true;
            sessionStorage.setItem('username', username);
            sessionStorage.setItem('role',this.userBackend.role);
 
            resolve(true);
          }
          else {
            this.authenticated = false;
            reject(false)
          }
        }, () => {
          window.alert('wrong username or password');
        })
    })
    return prm;
  }
  // async authenticate(username:any, password:any) {
  //    this.user=new Banker();
  //    this.user.userName=username;
  //    this.user.password=password;
     
  //   await this.getUser(this.user).then(
  //     (res)=>{
  //     this.userBackend.userName=res.userName;
  //     this.userBackend.password=res.password;
  //     this.userBackend.role=res.role;},
  //     (error)=>{
  //       alert("Login Invalid")
  //     }
  //   )
    // .subscribe(async (data)=>{
      
    //   this.userBackend=new Banker();
    //   this.userBackend.userName=data.userName;
    //   this.userBackend.password=data.password;
    //   this.userBackend.role=data.role;
    // });
  //   if(this.user.userName===this.userBackend.userName && this.user.password===this.userBackend.password){
  //     this.authenticated=true;
  //     sessionStorage.setItem('username', username);
  //     sessionStorage.setItem('role',this.userBackend.role);
  //   }else{
  //     this.authenticated=false;

  //   }

  //   return this.authenticated;
  // }

  // getUser(User: Banker){
  //   return firstValueFrom(this.http.post<Banker>(this.authenticateURL, User));
  // }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    let role=sessionStorage.getItem('role');
    return !(user === null && role===null);
  }
  isUserAdmin(){
    let role=sessionStorage.getItem('role');
    return (role==='admin');
  }

  logOut() {
    sessionStorage.clear();
  }
}