import { Component, HostListener, OnInit } from '@angular/core';
import { AuthService } from './features/auth/services/auth.service';
import { NavigationStart, Router } from '@angular/router';
import { SessionService } from './services/session.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {  
  isMenuOpen: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private sessionService: SessionService) {
      this.router.events.subscribe(event => {
        if (event instanceof NavigationStart) {
          // Close menu when navigating to another page (when burger menu is activated)
          this.closeMenu();
        }
      });
  }

  public $isLogged(): Observable<boolean> {
    return this.sessionService.$isLogged();
  }

  isHomeRoute(): boolean {
    return this.router.url === '/';
  }

  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }

  @HostListener('document:click', ['$event'])
  closeMenu(event?: MouseEvent): void {
    if (!event) {
      // Close menu when navigating to another page (when burger menu is activated)
      this.isMenuOpen = false;
    } else {
      // Close menu when clicking outside the menu (when burger menu is activated)
      const targetElement = event.target as HTMLElement;
      const menuElement = document.querySelector('.mobile-menu') as HTMLElement;
      const burgerMenuIcon = document.querySelector('.burger-menu') as HTMLElement;
      if (menuElement && !menuElement.contains(targetElement) && !burgerMenuIcon.contains(targetElement)) {
        this.isMenuOpen = false;
      }
    }
  }

  title = 'MDD';
}
