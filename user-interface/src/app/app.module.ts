import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {PatientComponent} from './patient/patient.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AppointmentComponent} from './appointment/appointment.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule, MatInputModule, MatTableModule} from "@angular/material";


@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MatButtonModule,
        MatTableModule,
        MatInputModule,
        FormsModule,
        HttpClientModule
    ],
    declarations: [
        AppComponent,
        PatientComponent,
        AppointmentComponent
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
