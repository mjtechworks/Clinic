import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {PatientComponent} from './patient/patient.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AppointmentComponent} from './appointment/appointment.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule, MatInputModule, MatTableModule} from "@angular/material";
import {CookieService} from "ngx-cookie-service";
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {RouterModule} from "@angular/router";


@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MatButtonModule,
        MatTableModule,
        MatInputModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forRoot([
            {path: '', component: HomeComponent},
            {path: 'login', component: LoginComponent}])
    ],
    declarations: [
        AppComponent,
        PatientComponent,
        AppointmentComponent,
        LoginComponent,
        HomeComponent
    ],
    providers: [CookieService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
