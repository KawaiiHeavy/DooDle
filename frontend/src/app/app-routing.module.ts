import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { HomeComponent } from "./components/home/home.component";
import { LoginComponent } from "./components/login/login.component";
import { AdminpageComponent } from "./components/page/adminpage/adminpage.component";
import { StudentpageComponent } from "./components/page/studentpage/studentpage.component";
import { TrainerpageComponent } from "./components/page/trainerpage/trainerpage.component";
import { UserpageComponent } from "./components/page/userpage/userpage.component";
import { RegisterComponent } from "./components/register/register.component";

const routes: Routes = [
    {
        path: 'home',
        component: HomeComponent
    },
    {
        path: 'user',
        component: UserpageComponent
    },
    {
        path: 'admin',
        component: AdminpageComponent
    },
    {
        path: 'trainer',
        component: TrainerpageComponent
    },
    {
        path: 'student',
        component: StudentpageComponent
    },
    {
        path: 'auth/login',
        component: LoginComponent
    },
    {
        path: 'signup',
        component: RegisterComponent
    },
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }