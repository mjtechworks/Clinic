import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Appointment} from "../appointment/appointment";
import {AuthenticationService} from "./authentication.service";

@Injectable()
export class AppointmentService {
    private getAllAppointmentUrl = 'api/appointments/all';
    private addAppointmentUrl = 'api/appointments/add';


    constructor(private http: HttpClient, private authService: AuthenticationService) {
    }

    public getAllAppointment(): Observable<Appointment[]> {
        let headers = new HttpHeaders({'Authorization': 'Bearer ' + this.authService.getOauthToken()});
        let options = {
            headers: headers
        };

        return this.http.get<Appointment[]>(this.getAllAppointmentUrl, options);
    }

    public addAppointment(appointment: Appointment): Observable<Appointment> {
        let headers = new HttpHeaders({'Authorization': 'Bearer ' + this.authService.getOauthToken()});
        let options = {
            headers: headers
        };

        return this.http.post<Appointment>(this.addAppointmentUrl, appointment, options);
    }

}