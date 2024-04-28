import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { SessionService } from '../services/session.service';

@Injectable({
  providedIn: 'root'
})
export class HomeRedirectGuard implements CanActivate {

  constructor(private sessionService: SessionService, private router: Router) {}

  canActivate(): boolean {
    if (this.sessionService.isLogged) {
      this.router.navigate(['/posts']);
      return false;
    }
    return true;
  }
}
