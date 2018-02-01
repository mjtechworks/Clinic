import {Service} from "./service";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {Weather} from "../domain/weather";
import {AuthenticationService} from "./authentication.service";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class WeatherService extends Service {
    private getWeatherUrl: string = 'api/appointments/weather?lat=${lat}&lon=${lon}';

    constructor(private http: HttpClient, private authService: AuthenticationService) {
        super(authService);
    }

    public getWeathers(lat: number, lon: number): Observable<Weather[]> {
        return this.http.get <Weather[]>(this.getWeatherUrl, super.getHeader());
    }

}