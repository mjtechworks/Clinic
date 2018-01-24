import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Appointment} from "../domain/appointment";
import {AuthenticationService} from "./authentication.service";
import {Service} from "./service";

@Injectable()
export class AppointmentService extends Service {
    private getAllAppointmentUrl = 'api/appointments/all?doctorEmail=';
    private addAppointmentUrl = 'api/appointments/add';
    private getAppointmentUrl = 'api/appointments/';
    private getAppointmentUpdateUrl = 'api/appointments/update';

    constructor(private http: HttpClient, private authService: AuthenticationService) {
        super(authService);
    }

    public getAllAppointment(): Observable<Appointment[]> {
        return this.http.get<Appointment[]>(this.getAllAppointmentUrl + this.authService.getUsername(), super.getHeader());
    }

    public getAllPatientAppointments(patientId: string): Observable<Appointment[]> {
        return this.http.get<Appointment[]>(this.getAllAppointmentUrl + this.authService.getUsername() + "&patientId=" + patientId, super.getHeader());
    }

    public getAppointment(id: string): Observable<Appointment> {
        return this.http.get<Appointment>(this.getAppointmentUrl + id, super.getHeader());
    }

    public addAppointment(appointment: Appointment): Observable<Appointment> {
        return this.http.post<Appointment>(this.addAppointmentUrl, appointment, super.getHeader());
    }

    public updateAppointment(appointment: Appointment): Observable<Appointment> {
        return this.http.put<Appointment>(this.getAppointmentUpdateUrl, appointment, super.getHeader());
    }

}