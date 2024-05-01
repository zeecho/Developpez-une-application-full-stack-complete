import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PostService } from 'src/app/services/post.service';
import { SessionService } from 'src/app/services/session.service';
import { TopicService } from 'src/app/services/topic.service';

@Component({
  selector: 'app-post-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class PostCreateComponent {
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
    private postService: PostService,
    private topicService: TopicService,
    private sessionService: SessionService) {
  }

  public back(): void {
    window.history.back();
  }

  public submit(): void {
    const createRequest = this.form.value;
    const userId = this.sessionService.sessionInformation!.id.toString();

    this.postService.create(userId, createRequest).subscribe({
      next: () => {
        this.router.navigate(['/posts']);
      },
      error: error => this.onError = true,
    });
  }
}
