import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ArticleService } from 'src/app/services/article.service';
import { SessionService } from 'src/app/services/session.service';
import { TopicService } from 'src/app/services/topic.service';

@Component({
  selector: 'app-article-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class ArticleCreateComponent {
  public form = this.fb.group({
    topic: [
      null,
      [
        Validators.required,
      ]
    ],
    title: [
      '',
      [
        Validators.required,
      ]
    ],
    content: [
      '',
      [
        Validators.required,
      ]
    ]
  });

  topics$ = this.topicService.all();

  public onError = false;

  constructor(private fb: FormBuilder,
    private router: Router,
    private articleService: ArticleService,
    private topicService: TopicService,
    private sessionService: SessionService) {
  }

  public back(): void {
    window.history.back();
  }

  public submit(): void {
    const createRequest = this.form.value;
    const userId = this.sessionService.sessionInformation!.id.toString();

    this.articleService.create(userId, createRequest).subscribe({
      next: () => {
        this.router.navigate(['/articles']);
      },
      error: error => this.onError = true,
    });
  }
}
