import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {DoctorComponent} from './patient/patient.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule
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
