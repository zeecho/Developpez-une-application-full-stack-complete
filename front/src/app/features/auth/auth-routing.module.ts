import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from 'src/app/pages/home/home.component';

const routes: Routes = [
  { title: 'MDD - Accueil', path: '', component: HomeComponent },
  { title: 'MDD - Se connecter', path: 'login', component: LoginComponent },
  { title: 'MDD - S\'inscrire', path: 'register', component: RegisterComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
