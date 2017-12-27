import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AuthenticationService {

    private tokenRequest = 'api/uaa/oauth/token';
    private currentAccount = 'api/uaa/current';

    constructor(private router: Router, private http: HttpClient, private cookieService: CookieService) {
    }

    obtainAccessToken(loginData) {
        let data = "scope=ui&grant_type=password&username=" + loginData.username + "&password=" + loginData.password;

        let headers = new HttpHeaders({
            'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
            'Authorization': 'Basic YnJvd3Nlcjo='
        });
        let options = {
            headers: headers
        };

        this.http.post(this.tokenRequest, data, options)
            .subscribe(
                data => this.saveToken(data),
                err => alert('Invalid Credentials'));
    }

    getCurrentAccount(): Observable<Object> {
        let token = this.getOauthToken();

        let headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
        let options = {
            headers: headers
        };

        return this.http.get(this.currentAccount, options);
    }

    saveToken(token) {
        let expireDate = new Date().getTime() + (1000 * token.expires_in);
        this.cookieService.set("access_token", token.access_token, expireDate);
        this.router.navigate(['/']);
    }


    getOauthToken(): String {
        return this.cookieService.get('access_token');
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