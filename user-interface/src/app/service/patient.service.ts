import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Patient} from "../patient/patient";
import {Injectable} from "@angular/core";
import {CookieService} from "ngx-cookie-service";

@Injectable()
export class PatientService {

    private getAllPatientUrl = 'api/patients/all';
    private addPatientUrl = 'api/patients/add';

    constructor(private http: HttpClient, private cookieService: CookieService) {
    }

    public getAllPatient(): Observable<Patient[]> {
        let headers = new HttpHeaders({
            'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
            'Authorization': 'Bearer ' + this.cookieService.get('access_token')
        });

        let options = {
            headers: headers
        };

        return this.http.get<Patient[]>(this.getAllPatientUrl, options);
    }

    public addPatient(patient: Patient): Observable<Patient> {
        let headers = new HttpHeaders({
            'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
            'Authorization': 'Bearer ' + this.cookieService.get('access_token')
        });

        let options = {
            headers: headers
        };

        return this.http.post<Patient>(this.addPatientUrl, patient, options);
    }

}