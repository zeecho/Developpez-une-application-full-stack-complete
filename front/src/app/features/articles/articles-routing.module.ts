import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArticleListComponent } from './components/list/list.component';
import { ArticleCreateComponent } from './components/create/create.component';
import { ArticleDetailComponent } from './components/detail/detail.component';

const routes: Routes = [
  { path: '', title: 'Articles', component: ArticleListComponent },
  { path: 'detail/:id', title: 'Article - detail', component: ArticleDetailComponent },
  { path: 'create', title: 'Créer un nouvel article', component: ArticleCreateComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArticlesRoutingModule {
}
