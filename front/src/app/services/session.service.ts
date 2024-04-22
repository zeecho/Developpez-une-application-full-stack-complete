import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { SessionInformation } from '../interfaces/sessionInformation.interface';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public isLogged = false;
  public sessionInformation: SessionInformation | undefined;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  constructor() {
    const sessionData = localStorage.getItem('session');
    if (sessionData) {
      this.sessionInformation = JSON.parse(sessionData);
      this.isLogged = true;
      this.next();
    }
  }

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public logIn(user: SessionInformation): void {
    this.sessionInformation = user;
    this.isLogged = true;
    this.next();
    localStorage.setItem('session', JSON.stringify(user));
  }

  public logOut(): void {
    this.sessionInformation = undefined;
    this.isLogged = false;
    this.next();
    localStorage.removeItem('session');
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }
}
