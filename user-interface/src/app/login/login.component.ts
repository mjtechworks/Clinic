import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    providers: [AuthenticationService],
    encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {
    public loginData = {username: "", password: ""};

    constructor(private authService: AuthenticationService, private router: Router) {
    }

    ngOnInit() {
        if (this.authService.isUserLogin()) {
            this.router.navigate(['/']);
        }
    }

    login() {
        this.authService.obtainAccessToken(this.loginData);
    }

    createAccount() {
        this.router.navigate(['/create-account']);
    }

}
