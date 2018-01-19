import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Appointment} from "../domain/appointment";
import {AuthenticationService} from "./authentication.service";
import {Service} from "./service";

@Injectable()
export class AppointmentService extends Service {
    private getAllAppointmentUrl = 'api/appointments/all';
    private addAppointmentUrl = 'api/appointments/add';


    constructor(private http: HttpClient, private authService: AuthenticationService) {
        super(authService);
    }

    public getAllAppointment(): Observable<Appointment[]> {
        return this.http.get<Appointment[]>(this.getAllAppointmentUrl, super.getHeader());
    }

    public addAppointment(appointment: Appointment): Observable<Appointment> {
        return this.http.post<Appointment>(this.addAppointmentUrl, appointment, super.getHeader());
    }

}