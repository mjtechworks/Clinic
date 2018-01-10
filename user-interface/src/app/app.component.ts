import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AuthenticationService} from "./service/authentication.service";
import {Router} from "@angular/router";
import {Doctor} from "./domain/doctor";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    providers: [AuthenticationService],
})
export class AppComponent implements OnInit {
    @ViewChild('sidenav') sidenav: ElementRef;
    @ViewChild('main') main: ElementRef;
    private doctor: Doctor = new Doctor();

    constructor(private service: AuthenticationService, private router: Router) {
    }

    ngOnInit(): void {
        if (this.service.isUserLogin()) {
            this.service.getCurrentAccount().subscribe(data => {
                this.doctor = data;
            });
        }
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
