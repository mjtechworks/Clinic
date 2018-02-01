import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Patient} from "../domain/patient";
import {Injectable} from "@angular/core";
import {AuthenticationService} from "./authentication.service";
import {Service} from "./service";

@Injectable()
export class PatientService extends Service {

    private getAllPatientUrl = 'api/patients/all?doctorEmail=';
    private addPatientUrl = 'api/patients/add';
    private getPatientUrl = 'api/patients/';
    private updatePatientUrl = 'api/patients/update';

    constructor(private http: HttpClient, private authService: AuthenticationService) {
        super(authService);
    }

    public getAllPatient(): Observable<Patient[]> {
        return this.http.get<Patient[]>(this.getAllPatientUrl + this.authService.getUsername(), super.getHeader());
    }

    public addPatient(patient: Patient): Observable<Patient> {
        return this.http.post<Patient>(this.addPatientUrl, patient, super.getHeader());
    }

    public getPatient(id: string): Observable<Patient> {
        return this.http.get<Patient>(this.getPatientUrl + id, super.getHeader());
    }

    public updatePatient(patient: Patient): Observable<Patient> {
        return this.http.put<Patient>(this.updatePatientUrl, patient, super.getHeader());
    }

}