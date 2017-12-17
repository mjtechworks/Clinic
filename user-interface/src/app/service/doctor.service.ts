import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "./authentication.service";
import {Doctor} from "../domain/doctor";
import {Observable} from "rxjs/Observable";

@Injectable()
export class DoctorService {

    private createDoctorUrl = "api/doctors/create";

    constructor(private http: HttpClient, private authService: AuthenticationService) {
    }

    public createDoctor(doctor: Doctor): Observable<boolean> {
        return this.http.post<boolean>(this.createDoctorUrl, doctor);
    }

}