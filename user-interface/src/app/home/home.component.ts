import {ChangeDetectionStrategy, Component, OnInit, TemplateRef, ViewChild, ViewEncapsulation} from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";
import {Subject} from "rxjs/Subject";
import {CalendarEventAction} from 'angular-calendar';
import {
    startOfDay,
    endOfDay,
    subDays,
    addDays,
    endOfMonth,
    isSameDay,
    isSameMonth,
    addHours
} from 'date-fns';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Weather} from "../domain/weather";
import {WeatherService} from "../service/weather.service";
import {Doctor} from "../domain/doctor";
import {DomSanitizer} from "@angular/platform-browser";
import {Appointment} from "../domain/appointment";
import {AppointmentService} from "../service/appointment.service";
import {CalendarAppointment} from "../domain/calendar-appointment";
import {Router} from "@angular/router";

const colors: any = {
    red: {
        primary: '#ad2121',
        secondary: '#FAE3E3'
    },
    blue: {
        primary: '#1e90ff',
        secondary: '#D1E8FF'
    },
    yellow: {
        primary: '#e3bc08',
        secondary: '#FDF1BA'
    }
};

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
    changeDetection: ChangeDetectionStrategy.OnPush,
    providers: [AuthenticationService, NgbModal, WeatherService, AppointmentService],
    encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {

    @ViewChild('modalContent') modalContent: TemplateRef<any>;
    view: string = 'month';
    viewDate: Date = new Date();
    refresh: Subject<any> = new Subject();
    private weathers$;
    private isCelsius = true;
    private activeDayIsOpen: boolean = true;
    private events: CalendarAppointment[] = [];

    private modalData: {
        action: string;
        event: CalendarAppointment;
    };

    private actions: CalendarEventAction[] = [
        {
            label: '<i class="fa fa-fw fa-pencil"></i>',
            onClick: ({event}: { event: CalendarAppointment }): void => {
                this.handleEvent('Edited', event);
            }
        },
        {
            label: '<i class="fa fa-fw fa-times"></i>',
            onClick: ({event}: { event: CalendarAppointment }): void => {
                this.events = this.events.filter(iEvent => iEvent !== event);
                this.handleEvent('Deleted', event);
            }
        }
    ];

    constructor(private service: AuthenticationService, private modal: NgbModal, private weatherService: WeatherService,
                private sanitizer: DomSanitizer, private appointmentService: AppointmentService, private router: Router) {
    }

    ngOnInit() {
        this.service.checkCredentials();
        this.weathers$ = this.weatherService.weathers();
        this.service.getCurrentAccount().subscribe(data => this.getWeather(data));
        this.appointmentService.getAllAppointment().subscribe(data => this.convertAppointmentToEvent(data));
    }

    private getWeather(doctor: Doctor) {
        this.weatherService.loadAll(doctor.latitude, doctor.longitude);
    }

    private convertAppointmentToEvent(appointments: Appointment[]) {
        appointments.forEach(appointment => {
            this.events.push({
                id: appointment.id,
                start: appointment.startDate,
                end: appointment.endDate,
                title: 'Open Appointment',
                color: colors.red,
                actions: this.actions
            });
            this.refresh.next();
        });
    }

    private dayClicked({date, events}: { date: Date; events: CalendarAppointment[] }): void {
        if (isSameMonth(date, this.viewDate)) {
            if (
                (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
                events.length === 0
            ) {
                this.activeDayIsOpen = false;
            } else {
                this.activeDayIsOpen = true;
                this.viewDate = date;
            }
        }
    }

    private handleEvent(action: string, event: CalendarAppointment): void {
        this.modalData = {event, action};
        this.router.navigate(['appointment/update/' + event.id]);
    }

    private getWeatherImage(weather: Weather) {
        let photoDescription = weather.weatherDescription.replace(' ', '_');
        let photoUrl = '/assets/' + photoDescription + '.svg';
        return this.sanitizer.bypassSecurityTrustResourceUrl(photoUrl);
    }

    private clickTemperature(isCelsius: boolean) {
        this.isCelsius = isCelsius;
    }

    private calculateTemperature(temperature: number): number {
        if (this.isCelsius) {
            return this.convertKelvinToCelsius(temperature);
        } else {
            return this.convertKelvinToFahrenheit(temperature);
        }
    }

    private convertKelvinToCelsius(temperature: number): number {
        let celsius = temperature - 273.15;
        return Math.round(celsius);
    }

    private convertKelvinToFahrenheit(temperature: number): number {
        let fahrenheit = temperature * 9 / 5 - 459.67;
        return Math.round(fahrenheit);
    }

}
