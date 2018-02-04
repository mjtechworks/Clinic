import {Service} from "./service";
import {Injectable} from "@angular/core";
import {Weather} from "../domain/weather";
import {AuthenticationService} from "./authentication.service";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject} from "rxjs/BehaviorSubject";

@Injectable()
export class WeatherService extends Service {
    private getWeatherUrl: string = 'api/appointments/weather';
    private _weathers: BehaviorSubject<Weather[]>;
    private dataStore: {
        weathers: Weather[]
    };

    constructor(private http: HttpClient, private authService: AuthenticationService) {
        super(authService);
        this.dataStore = {weathers: []};
        this._weathers = <BehaviorSubject<Weather[]>> new BehaviorSubject(this.dataStore.weathers);
    }

    public weathers() {
        return this._weathers.asObservable();
    }

    loadAll(lat: number, lon: number) {
        this.http.get<Weather[]>(this.getWeatherUrl + "?lat=" + lat + "&lon=" + lon, super.getHeader()).subscribe(data => {
            this.dataStore.weathers = data;
            this._weathers.next(Object.assign({}, this.dataStore).weathers);
        }, error => console.log('Could not load weather!.'));
    }

}