import { HttpBackend, HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

interface EmployeeDetails{
  name:string;
  age:number;
  class:string;
}
interface ProductDetails{
  
    name:string;
    description:string;
    location:string;
    status:string
  }
interface Userdetails{
  id:BigInteger;
  name:string;
  email:string;
  roleName:string;

}

interface Task{
  
    developerId:number;
    taskId:number;
    taskName:String;
    taskDesc:String;
    taskStatus:String;
}


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})


export class AppComponent implements OnInit{
 
  
  title = 'ems';
  temp=10;
  temp1={
    name:"expeditors",
    class:"angular"
  };
  data:number[]=[10,20,30,40,50];
// constructor(){
//   console.log("constructor loaded");
// }
// ngOnInit():void{
//   console.log("onit loaded");
//   this.data=this.data.map((d:number)=>d*2);
empData!:EmployeeDetails
user:Userdetails[]=[];
task:Task[]=[];//task is the interface name

employeedetails:EmployeeDetails[]=[
  {
    name:"siri",
    age:6,
    class:"java"
  },
  {
    name:"divi",
    age:16,
    class:"android"
  },
  {
    name:"gowthami",
    age:20,
    class:"java"
  }
];
products:ProductDetails[]=[
  {
    name:"furniture",
    description:"4 legged furniture",
    location:"chennai",
    status:"delivered"
  },
  {
    name:"fan",
    description:"4 hand furniture",
    location:"pune",
    status:"not delivered"
  },
  {
    name:"cupboard",
    description:"best cupboard",
    location:"kolkata",
    status:"delivered"
  }
];


constructor(private http:HttpClient){
  console.log("constructor loaded");
}
ngOnInit(): void {
  console.log("onit loaded");
  // this.data=this.data.map((d:number)=>d*2);
  let request$=this.http.get("http://localhost:8080/GetUser");
  request$.subscribe((res:any)=>{
    console.log('res-->',res);
    this.user=res.data;
  })
}

getEmployeeDetails(employee:EmployeeDetails){
  this.empData=employee;
}


}




