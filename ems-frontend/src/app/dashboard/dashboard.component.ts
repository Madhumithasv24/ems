import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable,of } from 'rxjs';
import { Router } from '@angular/router';
import { AppService } from '../app.service';


// interface UserDetails{
//   id:number;
//   name:String;
//   email:String;
//   role:String
// }

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
showusers:boolean=false;

showtask:boolean=false;

showadmin:boolean=false;
showtaskformanager:boolean=false;

userdetails$!:Observable<any>;
taskdetails$!:Observable<any>;

constructor(
  private http: HttpClient,
  private router:Router,
  private appService:AppService

) {
}


  // employeeData: any[] = [];
//    [{
//     name:"sree",
//     email:"sree@gmail.com"},
//    {
//     name:"gowthami",
//     email:"gowthami@gmail.com"
//   }
// ]}


// constructor(private http: HttpClient)
//   {
//     let request$: Observable<any> = this.http.get("https://reqres.in/api/users?page=2");
//     request$.subscribe((res)=>{
//       console.log('table data',res);
//       this.employeeData = res.data;
//     })
  
 showAdmin() {
  this.showadmin=true;
 }

showUser(){
    // Check if the user is authenticated and has the role of Admin
    // const isAuthenticated = this.appService.isAuthenticated();
      this.appService.isAuthenticated().role=="Admin";
      this.showusers = true;
    }
  

showTask(){
  this.showtask=true;
}

showmanager(){
  this.showtaskformanager=true;
}

resetData(){
  this.showusers=false;
  this.showtask=false;
}
getuser() {
  let request$=this.http.get("http://localhost:8080/GetUser");
  request$.subscribe((res: any) => {
    console.log('res-->',res);
    this.showusers = true;
    this.showadmin=true;
    this.userdetails$= of(res.data);//of(reactive js operator) is used because res is not observable value since it is subscribed,so to convert res as observable we used of,since userdata is observable,(to assign normal object to a observable)
  })
}
  

gettask(){
  let data$=this.http.get("http://localhost:8080/getAllTask");
  data$.subscribe((res:any)=>{
    console.log('res-->',res);
    this.showtask=true;
    this.showtaskformanager=true;
    this.taskdetails$=of(res.data);

  })
}
viewData(e:any){
console.log('event emitter-->',e);
this.appService.setDetails(e);
this.router.navigate(['viewdetails/'+e.data.id])
}

viewtask(e:any){
  console.log('event emitter-->',e);
  this.appService.setTask(e);
this.router.navigate(['edit-task/'+e.data])

}
  }



