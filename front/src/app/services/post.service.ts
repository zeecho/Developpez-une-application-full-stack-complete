import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../interfaces/post.interface';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private pathService = 'api/posts';

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.pathService);
  }

  public allSubscribed(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(`${this.pathService}/subscribed`);
  }

  public detail(id: string): Observable<Post> {
    return this.httpClient.get<Post>(`${this.pathService}/${id}`);
  }

  public create(formData: Object): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/create`, formData);
  }
}
