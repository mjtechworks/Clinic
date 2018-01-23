import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AppointmentService} from "../service/appointment.service";
import {Appointment} from "../domain/appointment";
import {AuthenticationService} from "../service/authentication.service";
import {Patient} from "../domain/patient";
import {PatientService} from "../service/patient.service";
import {MatSnackBar} from "@angular/material";

@Component({
    selector: 'app-appointment',
    templateUrl: './appointment.component.html',
    styleUrls: ['./appointment.component.css'],
    providers: [AppointmentService, AuthenticationService, PatientService],
    encapsulation: ViewEncapsulation.None
})
export class AppointmentComponent implements OnInit {

    private model: Appointment;
    private patients: Patient[];

    constructor(private appointmentService: AppointmentService, private authService: AuthenticationService, private patientService: PatientService, public snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.authService.checkCredentials();

        this.model = new Appointment();

        this.patientService.getAllPatient().subscribe(data => {
            this.patients = data;
        });
    }

    private onSubmit() {
        this.model.doctorEmail = this.authService.getUsername();

        this.appointmentService.addAppointment(this.model).subscribe(data => this.displayMessage("An appointment was established !"));
    }

    private displayMessage(message) {
        this.snackBar.open(message, "", {
            duration: 5000,
        });
    }

}
