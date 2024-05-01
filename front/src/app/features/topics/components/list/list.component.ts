import { Component } from '@angular/core';
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
  
  public isSubscribed: boolean[] = [];

  public userId: string;

  constructor(
    private sessionService: SessionService,
    private topicService: TopicService,
    private userService: UserService
  ) {
    this.userId = this.sessionService.sessionInformation!.id.toString();
    this.fetchUserTopics();
   }

  public subscribe(topicId: number, index: number): void {
    if (!this.isSubscribed[index]) {
      this.topicService.subscribe(topicId.toString()).subscribe(_ => this.fetchUserTopics());
    }
  }

  private fetchUserTopics(): void {
    this.userService
      .getCurrentUser()
      .subscribe((user: User) => {
        this.topics$.subscribe((topics: Topic[]) => {
          this.isSubscribed = topics.map(topic => user.topics.includes(topic.id));
        });
      });
  }
}
