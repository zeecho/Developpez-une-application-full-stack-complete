import { Component } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Post } from 'src/app/interfaces/post.interface';
import { PostService } from 'src/app/services/post.service';
import { SessionService } from 'src/app/services/session.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class PostListComponent {
  public showOrderMenu = false;
  public posts$: Observable<Post[]> = this.postService.allSubscribed();

  constructor(private postService: PostService) {
  }

  public toggleOrderMenu() {
    this.showOrderMenu = !this.showOrderMenu;
  }

  public orderBy(attribute: keyof Post, ascending: boolean): void {
    this.showOrderMenu = false;
    this.posts$ = this.posts$.pipe(
      map(posts => posts.sort((a, b) => {
        const valueA = a[attribute];
        const valueB = b[attribute];

        if (valueA !== undefined && valueB !== undefined) {
          if (attribute === 'createdAt') {
            const dateA = new Date(valueA).getTime();
            const dateB = new Date(valueB).getTime();
            return ascending ? dateA - dateB : dateB - dateA;
          } else {
            if (valueA < valueB) return -1;
            if (valueA > valueB) return 1;
          }
        }
        return 0;
      }))
    );
  }
}
