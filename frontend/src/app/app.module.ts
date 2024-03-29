import { CommonModule } from '@angular/common';  
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatSliderModule } from '@angular/material/slider';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatDividerModule } from '@angular/material/divider';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort'; 


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
import { TestCreatingComponent } from './components/test-creating/test-creating.component';
import { QuestionCreatingComponent } from './components/question-creating/question-creating.component';
import { AnswerCreatingComponent } from './components/answer-creating/answer-creating.component';
import { FilterPipe } from './components/testing/filter.pipe';
import { TestDetailInfoComponent } from './components/test-detail-info/test-detail-info.component';
import { ResultDetailInfoComponent } from './components/result-detail-info/result-detail-info.component';
import { TestEditingComponent } from './components/test-editing/test-editing.component';


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
    TestCreatingComponent,
    QuestionCreatingComponent,
    AnswerCreatingComponent,
    FilterPipe,
    TestDetailInfoComponent,
    ResultDetailInfoComponent,
    TestEditingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CommonModule,
    HttpClientModule,
    MatSliderModule,
    MatCardModule,
    MatInputModule,
    MatDividerModule,
    BrowserAnimationsModule,
    MatCheckboxModule,
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule
    
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent],
  entryComponents: [
    MainComponent,
  ]
})
export class AppModule { }
