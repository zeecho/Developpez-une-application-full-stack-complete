import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostListComponent } from './components/list/list.component';
import { PostCreateComponent } from './components/create/create.component';
import { PostDetailComponent } from './components/detail/detail.component';

const routes: Routes = [
  { path: '', title: 'Articles', component: PostListComponent },
  { path: 'detail/:id', title: 'Article - detail', component: PostDetailComponent },
  { path: 'create', title: 'Créer un nouvel article', component: PostCreateComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostRoutingModule {
}
