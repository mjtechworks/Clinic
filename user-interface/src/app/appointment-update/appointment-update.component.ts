import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../service/authentication.service";
import {Patient} from "../domain/patient";
import {Appointment} from "../domain/appointment";
import {MatSnackBar} from "@angular/material";
import {PatientService} from "../service/patient.service";
import {AppointmentService} from "../service/appointment.service";
import {Status} from "../domain/status";

@Component({
    selector: 'app-appointment-update',
    templateUrl: './appointment-update.component.html',
    styleUrls: ['./appointment-update.component.css'],
    providers: [PatientService, AppointmentService],
    encapsulation: ViewEncapsulation.None
})
export class AppointmentUpdateComponent implements OnInit {

    private patient: Patient;
    private appointment: Appointment;

    constructor(private route: ActivatedRoute, private patientService: PatientService,
                public snackBar: MatSnackBar, private authService: AuthenticationService,
                public appointmentService: AppointmentService, private router: Router) {
    }

    ngOnInit() {
        this.authService.checkCredentials();
        this.appointment = new Appointment();
        this.patient = new Patient();
        this.route.params.subscribe(params => {
            this.getAppointment(params['id']);
        });
    }

    private getAppointment(id: string) {
        this.appointmentService.getAppointment(id).subscribe(data => {
            this.appointment = data;
            this.appointment.startDate = new Date(this.appointment.startDate);
            this.appointment.endDate = new Date(this.appointment.endDate);
            this.getPatient(this.appointment.patientId);
        });
    }

    private getPatient(id: string) {
        this.patientService.getPatient(id).subscribe(data => this.patient = data);
    }

    private save() {
        this.appointmentService.updateAppointment(this.appointment).subscribe(data =>
            this.displayMessage('The appointment was updated successfully !'));
    }

    private displayMessage(message) {
        this.snackBar.open(message, "", {
            duration: 5000,
        });
    }

    private navigateToPatient() {
        this.router.navigate(['patient/update/' + this.patient.id]);
    }

    private done() {
        if (this.appointment.recommendation == null && this.appointment.remark == null) {
            this.displayMessage("Please write a remark and a recommendation to done the appointment !");
        } else {
            this.doneAppointment();
        }
    }

    private close() {
        if (this.appointment.reason == null) {
            this.displayMessage("Please write a reason to close the appointment !");
        } else {
            this.closeAppointment();
        }
    }

    private closeAppointment() {
        this.appointment.status = Status.CLOSE;
        this.appointmentService.updateAppointment(this.appointment).subscribe(data =>
            this.displayMessage('The appointment was closed successfully !'));
    }

    private doneAppointment() {
        this.appointment.status = Status.DONE;
        this.appointmentService.updateAppointment(this.appointment).subscribe(data =>
            this.displayMessage('The appointment was done successfully !'));
    }

}
