import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PatientService} from "../service/patient.service";
import {Patient} from "../domain/patient";
import {MatSnackBar} from "@angular/material";

@Component({
    selector: 'app-patient-update',
    templateUrl: './patient-update.component.html',
    styleUrls: ['./patient-update.component.css'],
    providers: [PatientService],
    encapsulation: ViewEncapsulation.None
})
export class PatientUpdateComponent implements OnInit {

    private model: Patient;

    constructor(private route: ActivatedRoute, private patientService: PatientService, public snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.model = new Patient();
        this.route.params.subscribe(params => this.getPatient(params['id']));
    }

    private getPatient(id: string) {
        this.patientService.getPatient(id).subscribe(data => {
            this.model = data;
            this.model.dateOfBirth = new Date(this.model.dateOfBirth);
        });
    }

    private onSubmit() {
        this.patientService.updatePatient(this.model).subscribe(data => {
            this.displayMessage("The patient was updated successfully !");
        })
    }

    private displayMessage(message) {
        this.snackBar.open(message, "", {
            duration: 5000,
        });
    }

}
