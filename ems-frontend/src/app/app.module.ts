import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { TableComponent } from './sharedmoduleone/table/table.component'
import { LoginComponent } from './login/login.component';
import { SharedmoduleoneModule } from './sharedmoduleone/sharedmoduleone.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppService } from './app.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DetailsComponent } from './details/details.component';
import { ViewdetailsComponent } from './viewdetails/viewdetails.component';
import { TaskdetailsComponent } from './taskdetails/taskdetails.component';
import { EditTaskComponent } from './edit-task/edit-task.component';

@NgModule({
  declarations: [
    AppComponent,
    
    TableComponent,
    LoginComponent,
    DashboardComponent,
    DetailsComponent,
    ViewdetailsComponent,
    TaskdetailsComponent,
    EditTaskComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedmoduleoneModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }