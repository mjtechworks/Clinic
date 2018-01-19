import {AuthenticationService} from "./authentication.service";
import {HttpHeaders} from "@angular/common/http";

export abstract class Service {

    constructor(private service: AuthenticationService) {
    }

    protected getHeader(): { headers: HttpHeaders } {
        let headers = new HttpHeaders({'Authorization': 'Bearer ' + this.service.getOauthToken()});
        return {
            headers: headers
        };
    }

}