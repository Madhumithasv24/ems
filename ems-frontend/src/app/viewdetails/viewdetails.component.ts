import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-viewdetails',
  templateUrl: './viewdetails.component.html',
  styleUrls: ['./viewdetails.component.scss']
})
export class ViewdetailsComponent {

detailsFormGroup!:FormGroup;



constructor(private fb:FormBuilder,private appService:AppService,private http:HttpClient){

}
ngOnInit():void{
  this.detailsFormGroup=this.fb.group({
    id:new FormControl({value:'',disabled:true}),
    name:[''],
    email:[''],
    password:['']
  
  }
  );
  this.appService.getDetails().subscribe((res)=>{
    console.log('app details-->',res)
    this.detailsFormGroup.patchValue({//patch is to edit any particular data
      id: res.data.id,
      name:res.data.name,
      email:res.data.email,
      password:res.data.password
    })
  })
  // let idControl:FormControl=this.detailsFormGroup.get("id") as FormControl;
  // idControl.disable();
}
onSubmit(){
  console.log('on submit-->',this.detailsFormGroup.getRawValue());
    let request$ =  this.http.put("http://localhost:8080/updateuser", this.detailsFormGroup.getRawValue());
    request$.subscribe((res:any) =>{
      console.log('res ===>', res);
    });
  }
}

