import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Topic } from 'src/app/interfaces/topic.interface';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { TopicService } from 'src/app/services/topic.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent {
  public userId: string;

  public topics$: Observable<Topic[]>;

  profileForm = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
    email: ['', [Validators.required, Validators.email]]
  });

  public onError = false;
  public errorMessage = "Une erreur est survenue";
  public onSuccess = false;

  constructor(
    private sessionService: SessionService,
    private userService: UserService,
    private topicService: TopicService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.userId = this.sessionService.sessionInformation!.id.toString();
    this.topics$ = this.topicService.allSubscribed(this.userId);

    this.userService.getById(this.userId).subscribe((user: User) => {
      this.profileForm.patchValue({
        username: user.username,
        email: user.email
      });
    });
  }

  public updateProfile(): void {
        this.onSuccess = false;
        this.onError = false;
        const updatedProfile = this.profileForm.value;
        this.userService.updateProfile(updatedProfile).subscribe({
          next: () => {
            this.onSuccess = true;
          },
          error: (error) => {
            this.onError = true;
            this.errorMessage = error.error;
          }
        });
  }

  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate([''])
  }

  private fetchUserTopics(): void {
    this.userService.getById(this.userId).subscribe((user: User) => {
      this.topicService.allSubscribed(this.userId).subscribe((topics: Topic[]) => {
        this.topics$ = of(topics);
      });
    });
  }

  public unsubscribe(topicId: number): void {
      this.topicService.unsubscribe(topicId.toString(), this.userId).subscribe(_ => {
        this.fetchUserTopics();
      });
  }
}
