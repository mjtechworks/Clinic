import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AppointmentService} from "../service/appointment.service";
import {Appointment} from "../domain/appointment";
import {AuthenticationService} from "../service/authentication.service";

@Component({
    selector: 'app-appointment',
    templateUrl: './appointment.component.html',
    styleUrls: ['./appointment.component.css'],
    providers: [AppointmentService, AuthenticationService],
    encapsulation: ViewEncapsulation.None
})
export class AppointmentComponent implements OnInit {

    private model: Appointment;
    private appointments: Appointment[];

    constructor(private appointmentService: AppointmentService, private authService : AuthenticationService) {
    }

    ngOnInit() {
        this.authService.checkCredentials();

        this.model = new Appointment();

        this.appointmentService.getAllAppointment().subscribe(data => {
            this.appointments = data;
        });
    }

    private onSubmit() {
        this.appointmentService.addAppointment(this.model).subscribe(data => {
            this.appointments.push(data);
        });
    }

}
