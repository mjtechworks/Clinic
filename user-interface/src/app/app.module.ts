import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {PatientComponent} from './patient/patient.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AppointmentComponent} from './appointment/appointment.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule,
    MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule,
    MatInputModule, MatListModule, MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule,
    MatProgressSpinnerModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule, MatSortModule, MatStepperModule,
    MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule
} from "@angular/material";
import {CookieService} from "ngx-cookie-service";
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {RouterModule} from "@angular/router";
import {DoctorComponent} from './doctor/doctor.component';
import {CalendarModule} from "angular-calendar";
import {CommonModule} from "@angular/common";
import {NgbModalModule} from '@ng-bootstrap/ng-bootstrap';
import {FilterPipe} from "./pipe/filter";
import {OwlDateTimeModule, OwlNativeDateTimeModule} from "ng-pick-datetime";
import {DoctorUpdateComponent} from './doctor-update/doctor-update.component';
import {AgmCoreModule} from '@agm/core';
import {PatientUpdateComponent} from './patient-update/patient-update.component';
import {AppointmentUpdateComponent} from './appointment-update/appointment-update.component';


@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MatAutocompleteModule,
        MatButtonModule,
        MatButtonToggleModule,
        MatCardModule,
        MatCheckboxModule,
        MatChipsModule,
        MatDatepickerModule,
        MatDialogModule,
        MatExpansionModule,
        MatGridListModule,
        MatIconModule,
        MatInputModule,
        MatListModule,
        MatMenuModule,
        MatNativeDateModule,
        MatPaginatorModule,
        MatProgressBarModule,
        MatProgressSpinnerModule,
        MatRadioModule,
        MatRippleModule,
        MatSelectModule,
        MatSidenavModule,
        MatSliderModule,
        MatSlideToggleModule,
        MatSnackBarModule,
        MatSortModule,
        MatTableModule,
        MatTabsModule,
        MatToolbarModule,
        MatTooltipModule,
        MatStepperModule,
        FormsModule,
        MatSnackBarModule,
        HttpClientModule,
        CalendarModule.forRoot(),
        NgbModalModule.forRoot(),
        CommonModule,
        OwlDateTimeModule,
        OwlNativeDateTimeModule,
        RouterModule.forRoot([
            {path: '', component: HomeComponent},
            {path: 'login', component: LoginComponent},
            {path: 'create-account', component: DoctorComponent},
            {path: 'appointments', component: AppointmentComponent},
            {path: 'patients', component: PatientComponent},
            {path: 'doctor/update', component: DoctorUpdateComponent},
            {path: 'patient/update/:id', component: PatientUpdateComponent},
            {path: 'appointment/update/:id', component: AppointmentUpdateComponent}
        ]),
        AgmCoreModule.forRoot({
            apiKey: 'AIzaSyBVauZN-pE8Gd1ygY2f6Kxb00WyG_sGI40'
        })
    ],
    declarations: [
        AppComponent,
        PatientComponent,
        AppointmentComponent,
        LoginComponent,
        HomeComponent,
        DoctorComponent,
        FilterPipe,
        DoctorUpdateComponent,
        PatientUpdateComponent,
        AppointmentUpdateComponent
    ],
    providers: [CookieService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
