import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";

@Injectable()
export class AuthenticationService {

    private loginDoctor = 'api/doctors/login';

    constructor(private router: Router, private http: HttpClient, private cookieService: CookieService) {
    }

    obtainAccessToken(loginData) {
        let params = new URLSearchParams();
        params.append('username', loginData.username);
        params.append('password', loginData.password);
        params.append('grant_type', 'password');
        params.append('client_id', 'clientIdPassword');

        let headers = new HttpHeaders({
            'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
            'Authorization': 'Basic ' + btoa("clientIdPassword:secret")
        });
        let options = {
            headers: headers
        };

        this.http.post(this.loginDoctor, params.toString(), options)
            .subscribe(
                data => this.saveToken(data),
                err => alert('Invalid Credentials'));
    }

    saveToken(token) {
        let expireDate = new Date().getTime() + (1000 * token.expires_in);
        this.cookieService.set("access_token", token.access_token, expireDate);
        this.router.navigate(['/']);
    }


    checkCredentials() {
        if (!this.cookieService.check('access_token')) {
            this.router.navigate(['/login']);
        }
    }

    logout() {
        this.cookieService.delete('access_token');
        this.router.navigate(['/login']);
    }

}