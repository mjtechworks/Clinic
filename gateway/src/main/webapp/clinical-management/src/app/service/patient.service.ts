import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Patient} from "../patient/patient";
import {Injectable} from "@angular/core";

@Injectable()
export class PatientService {

    private getAllPatientUrl = 'http://localhost:80/patients/all';

    constructor(private http: HttpClient) {
    }

    public getAllPatient(): Observable<Patient[]> {
        return this.http.get<Patient[]>(this.getAllPatientUrl);
    }

}