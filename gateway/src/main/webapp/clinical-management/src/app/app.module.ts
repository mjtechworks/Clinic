import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {DoctorComponent} from './patient/patient.component';
import {FormsModule} from "@angular/forms";


@NgModule({
    imports: [
        BrowserModule,
        FormsModule
    ],
    declarations: [
        AppComponent,
        DoctorComponent
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
