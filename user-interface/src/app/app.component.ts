import {Component, ElementRef, ViewChild} from '@angular/core';
import {AuthenticationService} from "./service/authentication.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    providers: [AuthenticationService],
})
export class AppComponent {

    @ViewChild('sidenav') sidenav: ElementRef;
    @ViewChild('main') main: ElementRef;

    constructor(private service: AuthenticationService, private router: Router) {
    }

    private logout() {
        this.service.logout();
    }

    private navigate(url: String) {
        this.router.navigate([url]);
    }

    private openNav() {
        if (this.sidenav.nativeElement.style.width === "0px") {
            this.sidenav.nativeElement.style.width = "200px";
            this.main.nativeElement.style.marginLeft = "200px";
        } else {
            this.sidenav.nativeElement.style.width = "0px";
            this.main.nativeElement.style.marginLeft = "0px";
        }
    }
}
