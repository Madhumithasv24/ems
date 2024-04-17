import { NgModule, inject } from '@angular/core';
import {CanActivateFn, Router, RouterModule, Routes } from '@angular/router';
import { DetailsComponent } from './details/details.component';




import { LoginComponent } from './login/login.component';
import { AppService } from './app.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ViewdetailsComponent } from './viewdetails/viewdetails.component';
import { EditTaskComponent } from './edit-task/edit-task.component';
const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path:'dashboard/:roleName',
    canActivate:[isAuthenticated()],
    component:  DashboardComponent
  },
  {
    path: 'details',
    component: DetailsComponent
  },
  {
    path:'viewdetails/:id',//: refers to dynamic
    canActivate:[(isAuthenticated)],
    component:ViewdetailsComponent
  },

  {
    path:'edit-task/:id',
    canActivate:[(isAuthenticated)],
    component:EditTaskComponent
  },
  {
    path: 'login',
    canActivate:[LoginAuth()],
    component: LoginComponent
  },
  // {
  //   path: 'agent',
  //   loadChildren: () => import("./agent/agent.module").then((m)=> m.AgentModule)
  // }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export function isAuthenticated(): CanActivateFn {
  return()=>{
    const appService: AppService=inject(AppService);
    const router: Router=inject(Router);
    console.log('login check-->',appService.isAuthenticated());
    if(appService.isAuthenticated().isAuth=="true" && appService.isAuthenticated().role=="Admin")
     {
      // router.navigate(['dashboard'])
      return true;
    }
    if(appService.isAuthenticated().isAuth=="true" && appService.isAuthenticated().role=="Manager")
    {
      return true;
    }
    else{
      router.navigate(['login']);
      return false;
    }
  }

}

export function LoginAuth():CanActivateFn{
  return()=>{
    const appService:AppService=inject(AppService);
    const router:Router=inject(Router);
    console.log('login check -->',appService.isAuthenticated());
    if(appService.isAuthenticated().isAuth == "true"){
      //  && appService.isAuthenticated().role=="Admin"
      router.navigate(['dashboard/' + appService.isAuthenticated().role]);
      return false;
    }else{
      // router.navigate(['login']);
      return true;
    }
    }
}
//role is the key and roleName is the object