import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './components/list/profile.component';
import { ProfileChangePasswordComponent } from './components/change-password/change-password.component';

const routes: Routes = [
  { path: '', title: 'Profil', component: ProfileComponent },
  { path: 'change-password', title: 'MDD - Changement de mot de passe', component: ProfileChangePasswordComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule {
}
