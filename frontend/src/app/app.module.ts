import { CommonModule } from '@angular/common';  
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';
import { AuthorizationComponent } from './components/authorization/authorization.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { UserpageComponent } from './components/page/userpage/userpage.component';
import { AdminpageComponent } from './components/page/adminpage/adminpage.component';
import { TrainerpageComponent } from './components/page/trainerpage/trainerpage.component';
import { StudentpageComponent } from './components/page/studentpage/studentpage.component';
import { FormsModule } from '@angular/forms';

import { httpInterceptorProviders } from './auth/auth-interceptor';
import { TestingComponent } from './components/testing/testing.component';
import { TestPassingComponent } from './components/test-passing/test-passing.component';


@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    AuthorizationComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    UserpageComponent,
    AdminpageComponent,
    TrainerpageComponent,
    StudentpageComponent,
    TestingComponent,
    TestPassingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CommonModule,
    HttpClientModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent],
  entryComponents: [
    MainComponent,
  ]
})
export class AppModule { }
