import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Patient} from "../domain/patient";
import {Injectable} from "@angular/core";
import {AuthenticationService} from "./authentication.service";
import {Service} from "./service";

@Injectable()
export class PatientService extends Service {

    private getAllPatientUrl = 'api/patients/all?doctorEmail=';
    private addPatientUrl = 'api/patients/add';

    constructor(private http: HttpClient, private authService: AuthenticationService) {
        super(authService);
    }

    public getAllPatient(): Observable<Patient[]> {
        return this.http.get<Patient[]>(this.getAllPatientUrl + this.authService.getUsername(), super.getHeader());
    }

    public addPatient(patient: Patient): Observable<Patient> {
        return this.http.post<Patient>(this.addPatientUrl, patient, super.getHeader());
    }

}