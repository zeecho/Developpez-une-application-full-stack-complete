import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Post } from 'src/app/interfaces/post.interface';
import { Comment } from 'src/app/interfaces/comment.interface';
import { Topic } from 'src/app/interfaces/topic.interface';
import { User } from 'src/app/interfaces/user.interface';
import { PostService } from 'src/app/services/post.service';
import { CommentService } from 'src/app/services/comment.service';
import { SessionService } from 'src/app/services/session.service';
import { TopicService } from 'src/app/services/topic.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class PostDetailComponent implements OnInit {
  public post: Post | undefined;
  public author: User | undefined;
  public topic: Topic | undefined;
  public comments$: Observable<Comment[]> = this.commentService.all(this.route.snapshot.paramMap.get('id')!);

  public commentForm = this.fb.group({
    content: [
      '',
      [
        Validators.required,
      ]
    ],
    author: this.sessionService.sessionInformation!.id.toString(),
    post: this.route.snapshot.paramMap.get('id')
  });  
  
  public postId: string;

  public onError = false;
  public errorMessage = "Une erreur est survenue";

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private topicService: TopicService,
    private userService: UserService,
    private commentService: CommentService,
    private sessionService: SessionService,
    private fb: FormBuilder) {
      this.postId = this.route.snapshot.paramMap.get('id')!;
  }

  public ngOnInit(): void {
    this.fetchPost();
  }

  private updatePageTitle(): void {
    if (this.post) {
      document.title = this.post.title;
    }
  }

  public back(): void {
    window.history.back();
  }

  public submitComment(): void {
    const createRequest = this.commentForm.value;

    this.commentService.add(createRequest).subscribe({
      next: () => {
        this.commentForm.patchValue({
          content: ''
        });
        this.commentForm.get('content')?.clearValidators();
        this.commentForm.get('content')?.updateValueAndValidity();
        this.comments$ = this.commentService.all(this.postId);
      },
      error: (error) => {
        this.onError = true;
        this.errorMessage = error.error;
      }
    });
  }

  private fetchPost(): void {
    this.postService
      .detail(this.postId)
      .subscribe((post: Post) => {
        this.post = post;
        this.updatePageTitle();
        this.topicService
          .detail(post.topic.toString())
          .subscribe((topic: Topic) => {
            this.topic = topic;
          });
        this.userService
          .getCurrentUser()
          .subscribe((author: User) => {
            this.author = author;
          });
      })
  }
}
