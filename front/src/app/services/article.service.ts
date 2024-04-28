import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topic.interface';
import { Article } from '../interfaces/article.interface';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private pathService = 'api/articles';

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<Article[]> {
    return this.httpClient.get<Article[]>(this.pathService);
  }

  public allSubscribed(userId: number): Observable<Article[]> {
    return this.httpClient.get<Article[]>(`${this.pathService}/subscribed/${userId}`);
  }

  public detail(id: string): Observable<Article> {
    return this.httpClient.get<Article>(`${this.pathService}/${id}`);
  }

  public create(userId: string, formData: Object): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/create/${userId}`, formData);
  }
}
