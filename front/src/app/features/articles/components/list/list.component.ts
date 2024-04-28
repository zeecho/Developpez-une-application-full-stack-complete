import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Article } from 'src/app/interfaces/article.interface';
import { ArticleService } from 'src/app/services/article.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-article-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ArticleListComponent {
  public posts$: Observable<Article[]> = this.articleService.allSubscribed(this.sessionService.sessionInformation!.id);

  constructor(
    private router: Router,
    private articleService: ArticleService,
    private sessionService: SessionService) {
  }

  // TODO
  public orderBy(): void {

  }
}
