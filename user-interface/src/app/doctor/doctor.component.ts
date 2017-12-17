import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Doctor} from "../domain/doctor";
import {MatSnackBar} from "@angular/material";
import {DoctorService} from "../service/doctor.service";
import {AuthenticationService} from "../service/authentication.service";

@Component({
    selector: 'app-doctor',
    templateUrl: './doctor.component.html',
    styleUrls: ['./doctor.component.css'],
    providers: [DoctorService],
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
            this.doctorService.createDoctor(this.model).subscribe(data => this.obtainAccess(data),
                err => this.obtainAccess(false));
        } else {
            this.snackBar.open("The passwords aren't the same", "", {
                duration: 5000,
            });
        }
    }

    private obtainAccess(data) {
        if (data == true) {
            let loginData = {username: this.model.email, password: this.model.password};
            this.authService.obtainAccessToken(loginData);
        } else {
            this.snackBar.open("An error has occur !", "", {
                duration: 5000,
            });
        }
    }

}
