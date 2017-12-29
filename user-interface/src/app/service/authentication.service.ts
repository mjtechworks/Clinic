import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {Observable} from "rxjs/Observable";
import {MatSnackBar} from "@angular/material";

@Injectable()
export class AuthenticationService {

    private tokenRequest = 'api/uaa/oauth/token';
    private currentAccount = 'api/uaa/current';

    constructor(private router: Router, private http: HttpClient, private cookieService: CookieService, private snackBar: MatSnackBar) {
    }

    public obtainAccessToken(loginData) {
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
                err => this.displayMessage('Invalid Credentials'));
    }

    public getCurrentAccount(): Observable<Object> {
        let token = this.getOauthToken();

        let headers = new HttpHeaders({'Authorization': 'Bearer ' + token});
        let options = {
            headers: headers
        };

        return this.http.get(this.currentAccount, options);
    }

    public getOauthToken(): String {
        return this.cookieService.get('access_token');
    }

    public checkCredentials() {
        if (!this.cookieService.check('access_token')) {
            this.router.navigate(['/login']);
        }
    }

    public logout() {
        this.cookieService.delete('access_token');
        this.router.navigate(['/login']);
    }

    private saveToken(token) {
        let expireDate = new Date().getTime() + (1000 * token.expires_in);
        this.cookieService.set("access_token", token.access_token, expireDate);
        this.router.navigate(['/']);
    }

    private displayMessage(message) {
        this.snackBar.open(message, "", {
            duration: 5000,
        });
    }

}