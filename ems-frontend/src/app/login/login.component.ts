// import { Component } from '@angular/core';
// import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
// import { Router } from '@angular/router';
// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.scss']
// })
// export class LoginComponent {
//   login!: FormGroup;
//   constructor(private formbuilder: FormBuilder,
//     private router: Router){
//     this.IntializerForm();
//     this.login.valueChanges.subscribe((res)=>{
//       console.log('value change event --->', res);
//     })
//   }


//   IntializerForm(){
//     this.login = this.formbuilder.group({
//       userName: ['sample', [Validators.required]],
//       password: new FormControl('',[Validators.minLength(2), Validators.maxLength(7)] )
//     });
//   }
//   FormSubmit(){
//     console.log(this.login);
//     let formData = this.login.value;
//     console.log(this.login.get('userName'))
//     if(formData.userName != '' && formData.password != ''){
//       this.router.navigate(['agent/dashboard']);
//     }
//   }
// }

import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{
  loginFormGroup!: FormGroup;
  constructor(
    private formBuilder:FormBuilder,
    private http:HttpClient,
    private router:Router
  ){}

  ngOnInit(): void{
    this.loginFormGroup=this.formBuilder.group({
      userName:['sample'],
      password:new FormControl('')
    });
    console.log('loginFormGroup-->',this.loginFormGroup);
  }

  validateLogin(){
    let formData=this.loginFormGroup.value;
    console.log('formData-->',formData);
    let payload={
      userName:formData.userName,
      loginPassword: formData.password
    };
    let request$=this.http.post('http://localhost:8080/login',payload);
    request$.subscribe((res:any)=>{
        if(res.isAuth == "true" ){
          localStorage.setItem('data',JSON.stringify(res));
          this.router.navigate(['dashboard/'  + res.role]);
        }
        // if(res.roleName=="Admin"){
        // localStorage.setItem('data1',JSON.stringify(res));
        // this.router.navigate(['dashboard/' + res.roleName]);
        // }
    });
  }
}
