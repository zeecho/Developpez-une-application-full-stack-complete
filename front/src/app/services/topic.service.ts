import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topic.interface';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private pathService = 'api/topics';

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(this.pathService);
  }

  public allSubscribed(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.pathService}/subscribed`);
  }

  public detail(id: string): Observable<Topic> {
    return this.httpClient.get<Topic>(`${this.pathService}/${id}`);
  }

  public subscribe(id: string): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/${id}/subscribe`, null);
  }

  public unsubscribe(id: string): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/${id}/unsubscribe`, null);
  }
}
