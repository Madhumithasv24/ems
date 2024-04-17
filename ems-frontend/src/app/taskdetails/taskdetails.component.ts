import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Observable } from 'rxjs';


interface Task{
  
  developerId:number;
  taskId:number;
  taskName:String;
  taskDesc:String;
  taskStatus:String;
}

@Component({
  selector: 'app-taskdetails',
  templateUrl: './taskdetails.component.html',
  styleUrls: ['./taskdetails.component.scss']
})

export class TaskdetailsComponent {
  task:Task[] =[];
  constructor(private http:HttpClient){
    console.log("constructor loaded");
  }

  @Input('taskdata$')
set taskdata(tdata$:Observable<any>){
  if(typeof tdata$==='undefined'){
    return;
  }
  tdata$.subscribe((res:any)=>{
    console.log('child component data -->', res);
    this.task = res;
  })

}

@Output() taskdetailEmit=new EventEmitter;

editData(tdata:any){
  this.taskdetailEmit.emit({type:'tasks',tdata})
}
ngOnInit() : void {
  
  }
  
}
