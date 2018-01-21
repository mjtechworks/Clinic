import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {Doctor} from "../domain/doctor";
import {DoctorService} from "../service/doctor.service";
import {MatSnackBar} from "@angular/material";
import {PatientService} from "../service/patient.service";

@Component({
    selector: 'app-doctor-update',
    templateUrl: './doctor-update.component.html',
    styleUrls: ['./doctor-update.component.css'],
    providers: [DoctorService, AuthenticationService],
    encapsulation: ViewEncapsulation.None
})
export class DoctorUpdateComponent implements OnInit {

    private model: Doctor;

    constructor(private authService: AuthenticationService, private doctorService: DoctorService, public snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.authService.checkCredentials();
        this.model = new Doctor();
        this.authService.getCurrentAccount().subscribe(data => {
            this.model = data;
        });
    }

    private onSubmit() {
        this.doctorService.updateDoctor(this.model).subscribe(data => {
            this.displayMessage("The account was updated successfully !");
        });
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
