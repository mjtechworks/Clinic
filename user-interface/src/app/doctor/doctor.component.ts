import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Doctor} from "../domain/doctor";
import {MatSnackBar} from "@angular/material";
import {DoctorService} from "../service/doctor.service";
import {AuthenticationService} from "../service/authentication.service";

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

    constructor(public snackBar: MatSnackBar, private doctorService: DoctorService, private authService: AuthenticationService) {
    }

    ngOnInit() {
        this.model = new Doctor();
    }

    private onSubmit() {
        if (this.repeatPassword === this.model.password) {
            this.doctorService.createDoctor(this.model).subscribe(
                data => this.obtainAccess(data),
                err => this.displayMessage(err));
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

}
