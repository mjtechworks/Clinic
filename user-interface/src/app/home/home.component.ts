import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
    providers: [AuthenticationService],
    encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {

    constructor(private service: AuthenticationService) {
    }

    ngOnInit() {
        this.service.checkCredentials();
    }

    logout() {
        this.service.logout();
    }

}
