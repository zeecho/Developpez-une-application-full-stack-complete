import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/interfaces/topic.interface';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { TopicService } from 'src/app/services/topic.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-topic-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class TopicListComponent {
  public topics$: Observable<Topic[]> = this.topicService.all();

  // public topic: Topic | undefined;

  public isSubscribed: boolean[] = [];

  public userId: string;

  constructor(
    private route: ActivatedRoute,
    private sessionService: SessionService,
    private topicService: TopicService,
    private userService: UserService
  ) {
    this.userId = this.sessionService.sessionInformation!.id.toString();
    this.fetchUserTopics();
   }

  public subscribe(topicId: number, index: number): void {
    if (!this.isSubscribed[index]) {
      this.topicService.subscribe(topicId.toString(), this.userId).subscribe(_ => this.fetchUserTopics());
    }
  }

  private fetchUserTopics(): void {
    this.userService
      .getById(this.userId)
      .subscribe((user: User) => {
        this.topics$.subscribe((topics: Topic[]) => {
          this.isSubscribed = topics.map(topic => user.topics.includes(topic.id));
        });
      });
  }
}
