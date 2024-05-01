import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private pathService = 'api/user';

  constructor(private httpClient: HttpClient) { }

  public getCurrentUser(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}`);
  }

  public updateProfile(updatedProfile: Object): Observable<any> {
    return this.httpClient.put<void>(`${this.pathService}`, updatedProfile).pipe(
    );
  }

  public updatePassword(updatePassword: Object): Observable<any> {
    return this.httpClient.put<void>(`${this.pathService}/change-password`, updatePassword).pipe(
    );
  }
}
