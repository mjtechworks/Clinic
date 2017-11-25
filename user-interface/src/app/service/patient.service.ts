import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Patient} from "../patient/patient";
import {Injectable} from "@angular/core";

@Injectable()
export class PatientService {

    private getAllPatientUrl = 'api/patients/all';
    private addPatientUrl = 'api/patients/add';

    constructor(private http: HttpClient) {
    }

    public getAllPatient(): Observable<Patient[]> {
        return this.http.get<Patient[]>(this.getAllPatientUrl);
    }

    public addPatient(patient: Patient): Observable<Patient> {
        return this.http.post<Patient>(this.addPatientUrl, patient);
    }

}