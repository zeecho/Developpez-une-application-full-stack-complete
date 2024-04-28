import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UnauthGuard } from './guards/unauth.guard';
import { AuthGuard } from './guards/auth.guard';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { HomeRedirectGuard } from './guards/home-redirect.guard';

const routes: Routes = [
  {
    path: '',
    canActivate: [HomeRedirectGuard, UnauthGuard],
    loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'posts',
    canActivate: [AuthGuard],
    loadChildren: () => import('./features/post/post.module').then(m => m.PostModule)
  },
  {
    path: 'topics',
    canActivate: [AuthGuard],
    loadChildren: () => import('./features/topics/topics.module').then(m => m.TopicModule)
  },
  {
    path: 'profile',
    canActivate: [AuthGuard],
    loadChildren: () => import('./features/profile/profile.module').then(m => m.ProfileModule)
  },
  { path: '404', component: NotFoundComponent },
  { path: '**', redirectTo: '404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
