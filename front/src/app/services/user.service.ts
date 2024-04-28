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

  public getById(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/${id}`);
  }

  public updateProfile(userId: string, updatedProfile: Object): Observable<any> {
    return this.httpClient.put<void>(`${this.pathService}/${userId}`, updatedProfile).pipe(
    );
  }
}
