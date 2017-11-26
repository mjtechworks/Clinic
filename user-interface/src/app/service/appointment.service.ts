import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Appointment} from "../appointment/appointment";

@Injectable()
export class AppointmentService {
    private getAllAppointmentUrl = 'api/appointments/all';
    private addAppointmentUrl = 'api/appointments/add';


    constructor(private http: HttpClient) {
    }

    public getAllAppointment(): Observable<Appointment[]> {
        return this.http.get<Appointment[]>(this.getAllAppointmentUrl);
    }

    public addAppointment(appointment: Appointment): Observable<Appointment> {
        return this.http.post<Appointment>(this.addAppointmentUrl, appointment);
    }

}