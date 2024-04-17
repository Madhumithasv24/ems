import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'//no need to define the module as root indicateds that it can be injected to any component in the apllication 
})
export class AppService {
  private Details$=new BehaviorSubject <any>("");

  private DetailsTask$=new BehaviorSubject<any>("");

  constructor() { }
  setDetails(value: string){
    this.Details$.next(value);
  }
  getDetails(){
    return this.Details$.asObservable();
  }

  setTask(value:string){
    return this.DetailsTask$.next(value);
  }

  getTask(){
  return this.DetailsTask$.asObservable();
  }

  isAuthenticated(){
    let storagedata=localStorage.getItem("data");//data is key 
    console.log('localstorage data-->',storagedata);
    if(storagedata){
      let parseData=JSON.parse(storagedata);
      console.log('parsedData-->',parseData);
      return (parseData);
    }
    else{
      return false;
    }
  }
}
