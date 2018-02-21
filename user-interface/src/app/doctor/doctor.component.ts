import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Doctor} from "../domain/doctor";
import {MatSnackBar} from "@angular/material";
import {DoctorService} from "../service/doctor.service";
import {AuthenticationService} from "../service/authentication.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-doctor',
    templateUrl: './doctor.component.html',
    styleUrls: ['./doctor.component.css'],
    providers: [DoctorService, AuthenticationService],
    encapsulation: ViewEncapsulation.None
})
export class DoctorComponent implements OnInit {

    private model: Doctor;
    private repeatPassword: string;

    constructor(public snackBar: MatSnackBar, private doctorService: DoctorService, private authService: AuthenticationService, private router: Router) {
    }

    ngOnInit() {
        if (this.authService.isUserLogin()) {
            this.router.navigate(['/']);
        }

        this.model = new Doctor();
        this.model.latitude = 44.426543552183006;
        this.model.longitude = 26.104084253311157;
    }

    private onSubmit() {
        if (this.repeatPassword === this.model.password) {
            this.doctorService.createDoctor(this.model).subscribe(
                data => this.obtainAccess(data),
                () => this.displayMessage("Doctor with email " + this.model.email + " already exits !"));
        } else {
            this.displayMessage("The passwords aren't the same");
        }
    }

    private obtainAccess(data) {
        if (typeof data.message == 'undefined' && typeof data.status == 'undefined') {
            this.displayMessage("Doctor with email " + this.model.email + " has been created !");
            let loginData = {username: this.model.email, password: this.model.password};
            this.authService.obtainAccessToken(loginData);
        } else {
            this.displayMessage("Doctor with email " + this.model.email + " already exits !");
        }
    }

    private displayMessage(message) {
        this.snackBar.open(message, "", {
            duration: 5000,
        });
    }

    private mapClicked($event: any) {
        this.model.latitude = $event.coords.lat;
        this.model.longitude = $event.coords.lng;
    }

}
