import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../interfaces/comment.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private pathService = 'api/comments';

  constructor(private httpClient: HttpClient) { }

  public all(id: string): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>(`${this.pathService}/${id}`);
  }

  public add(formData: Object): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/add`, formData);
  }
}
