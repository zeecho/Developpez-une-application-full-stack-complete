import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ProfileChangePasswordComponent {
  passwordForm = this.fb.group({
    oldPassword: [
      '', 
      [
        Validators.required
      ]
    ],
    newPassword: [
      '', 
      [
        Validators.required, 
        Validators.minLength(8),
        Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-_?!])(?!.*\s).*$/)
      ]
    ]
  });

  public hideOld = true;
  public hideNew = true;
  public onError = false;
  public onSuccess = false;
  public errorMessage = "Une erreur est survenue";

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
  ) {
  }

  public back(): void {
    window.history.back();
  }

  public updatePassword(): void {
        this.onSuccess = false;
        this.onError = false;
        this.userService.updatePassword(this.passwordForm.value).subscribe({
          next: () => {
            this.onSuccess = true;
          },
          error: (error) => {
            this.onError = true;
            this.errorMessage = error.error;
          }
        });
  }
}
