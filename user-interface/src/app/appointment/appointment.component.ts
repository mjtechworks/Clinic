import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AppointmentService} from "../service/appointment.service";
import {Appointment} from "../domain/appointment";
import {AuthenticationService} from "../service/authentication.service";
import {Patient} from "../domain/patient";
import {PatientService} from "../service/patient.service";

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

    constructor(private appointmentService: AppointmentService, private authService : AuthenticationService, private patientService: PatientService) {
    }

    ngOnInit() {
        this.authService.checkCredentials();

        this.model = new Appointment();

        this.patientService.getAllPatient().subscribe(data => {
            this.patients = data;
        });
    }

    private onSubmit() {
        this.appointmentService.addAppointment(this.model).subscribe(data => {

        });
    }

}
