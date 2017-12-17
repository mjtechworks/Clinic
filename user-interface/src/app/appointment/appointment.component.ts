import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AppointmentService} from "../service/appointment.service";
import {Appointment} from "../domain/appointment";

@Component({
    selector: 'app-appointment',
    templateUrl: './appointment.component.html',
    styleUrls: ['./appointment.component.css'],
    providers: [AppointmentService],
    encapsulation: ViewEncapsulation.None
})
export class AppointmentComponent implements OnInit {

    private model: Appointment;
    private appointments: Appointment[];

    constructor(private appointmentService: AppointmentService) {
    }

    ngOnInit() {
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
