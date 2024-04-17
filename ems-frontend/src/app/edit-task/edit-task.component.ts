import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.scss']
})
export class EditTaskComponent {

  taskFormGroup!:FormGroup;

  constructor(private tf:FormBuilder,private appService:AppService,private http:HttpClient){

  }

  ngOnInit():void{
    this.taskFormGroup=this.tf.group({
      developerId:[''],  //new FormControl({value:'',disabled:true}),
      taskId:[''],
      taskName:[''],
      taskDesc:[''],
      taskStatus:['']
    
    }
    );
    this.appService.getTask().subscribe((res)=>{
      console.log('app details-->',res)
      this.taskFormGroup.patchValue({//patch is to edit any particular data
        developerId: res.tdata.developerId,
        taskId:res.tdata.taskId,
        taskName:res.tdata.taskName,
        taskDesc:res.tdata.taskDesc,
        taskStatus:res.tdata.taskStatus

      })
    })
    // let idControl:FormControl=this.detailsFormGroup.get("id") as FormControl;
    // idControl.disable();
  }

 //@Output() TaskEmit=new EventEmitter();

  onUpdate(){
    let formData=this.taskFormGroup.value;
    let payload={
      developerId:formData.developerId,
      taskId: formData.taskId,
      taskName: formData.taskName,
      taskDesc: formData.taskDesc,
      taskStatus: formData.taskStatus,
    };

    
    console.log('on submit-->',this.taskFormGroup.getRawValue());
      let request$ =  this.http.put("http://localhost:8080/updateTask", payload);
      request$.subscribe((res:any) =>{
        console.log('res ===>', res);
      });
    }
}
