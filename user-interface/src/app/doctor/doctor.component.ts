import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Doctor} from "../domain/doctor";
import {MatSnackBar} from "@angular/material";

@Component({
    selector: 'app-doctor',
    templateUrl: './doctor.component.html',
    styleUrls: ['./doctor.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class DoctorComponent implements OnInit {

    private model: Doctor;
    private repeatPassword: string;

    constructor(public snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.model = new Doctor();
    }

    private onSubmit() {
        if (this.repeatPassword === this.model.password) {
            // TODO
        } else {
            this.snackBar.open("The passwords aren't the same", "", {
                duration: 5000,
            });
        }
    }

}
