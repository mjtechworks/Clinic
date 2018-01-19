import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Doctor} from "../domain/doctor";
import {Observable} from "rxjs/Observable";
import {Service} from "./service";
import {AuthenticationService} from "./authentication.service";

@Injectable()
export class DoctorService extends Service {

    private createDoctorUrl = "api/doctors/create";
    private updateDoctorUrl = "api/doctors/update";

    constructor(private http: HttpClient, private authService: AuthenticationService) {
        super(authService);
    }

    public createDoctor(doctor: Doctor): Observable<Object> {
        return this.http.post<Object>(this.createDoctorUrl, doctor);
    }

    public updateDoctor(doctor: Doctor): Observable<Doctor> {
        return this.http.put<Doctor>(this.updateDoctorUrl, doctor, super.getHeader());
    }

}