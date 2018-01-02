import {Component} from '@angular/core';
import {AuthenticationService} from "./service/authentication.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    providers: [AuthenticationService],
})
export class AppComponent {

    constructor(private service: AuthenticationService, private router: Router) {
    }

    private logout() {
        this.service.logout();
    }

    private navigate(url: String) {
        this.router.navigate([url]);
    }

}
