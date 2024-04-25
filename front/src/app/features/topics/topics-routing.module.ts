import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { DetailComponent } from './components/detail/detail.component';
// import { FormComponent } from './components/form/form.component';
import { TopicListComponent } from './components/list/list.component';
// import { ArticleCreateComponent } from './components/create/create.component';

const routes: Routes = [
  { path: '', title: 'Thèmes', component: TopicListComponent },
  // { path: 'detail/:id', title: 'Sessions - detail', component: DetailComponent },
  // { path: 'article-create', title: 'Créer un nouvel article', component: ArticleCreateComponent },
  // { path: 'update/:id', title: 'Sessions - update', component: FormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArticlesRoutingModule {
}
