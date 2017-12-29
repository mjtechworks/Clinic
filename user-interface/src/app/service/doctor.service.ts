import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Doctor} from "../domain/doctor";
import {Observable} from "rxjs/Observable";

@Injectable()
export class DoctorService {

    private createDoctorUrl = "api/doctors/create";

    constructor(private http: HttpClient) {
    }

    public createDoctor(doctor: Doctor): Observable<Object> {
        return this.http.post<Object>(this.createDoctorUrl, doctor);
    }

}