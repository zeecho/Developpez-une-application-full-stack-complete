import { NgModule } from '@angular/core';
import { CommonModule, registerLocaleData } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
// import { ListComponent } from './components/list/list.component';
// import { FormComponent } from './components/form/form.component';
// import { DetailComponent } from './components/detail/detail.component';
import { ArticlesRoutingModule } from './articles-routing.module';
import localeFr from '@angular/common/locales/fr';
import { ArticleListComponent } from './components/list/list.component';
registerLocaleData(localeFr);

const materialModules = [
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatSnackBarModule,
  MatSelectModule
];

@NgModule({
  declarations: [
    ArticleListComponent
  ],
  imports: [
    CommonModule,
    FlexLayoutModule,
    ReactiveFormsModule,
    ArticlesRoutingModule,
    ...materialModules
  ]
})
export class ArticleModule { }
