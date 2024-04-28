import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Post } from 'src/app/interfaces/post.interface';
import { PostService } from 'src/app/services/post.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class PostListComponent {
  public posts$: Observable<Post[]> = this.postService.allSubscribed(this.sessionService.sessionInformation!.id);

  constructor(
    private router: Router,
    private postService: PostService,
    private sessionService: SessionService) {
  }

  // TODO
  public orderBy(): void {

  }
}
