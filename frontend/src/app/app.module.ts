import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';
import { AuthorizationComponent } from './components/authorization/authorization.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    AuthorizationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [
    MainComponent,
  ]
})
export class AppModule { }
