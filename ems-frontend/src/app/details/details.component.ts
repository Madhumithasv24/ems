import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
//event emitter must be imported from angular core
interface Userdetails{
  id:number;
  name:string;
  email:string;
  roleName:string;

}

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss']
})


export class DetailsComponent  {
  user:Userdetails[] =[];

  constructor(private http:HttpClient){
    console.log("constructor loaded");
  }
  @Input()id:number=0;
  @Input('userdatas$')   //@Input is used to give parent to child
  set userdatas(data$:Observable<any>){
    if(typeof data$==='undefined') {
      return;
    }
    data$.subscribe((res: any) => {
      console.log('child component data -->', res);
      this.user = res;
    })
  }
  editData(data:any){
    this.detailsEmit.emit({type:'users',data});
  }

  @Output() detailsEmit=new EventEmitter();//@Output is used to give child to parent

  ngOnInit() : void {
    // console.log("onit loaded");
    //  this.data=this.data.map((d:number)=>d*2);
    // let request$=this.http.get("http://localhost:8080/GetUser");
    // request$.subscribe((res: any) => {
    //   console.log('res-->',res);
    //   this.user=res.data;
    console.log('child component id-->',this.id);
    }
    
  }







  
 


  

