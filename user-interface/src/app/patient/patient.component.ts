import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Patient} from "../domain/patient";
import {PatientService} from "../service/patient.service";

@Component({
    selector: 'app-patient',
    templateUrl: './patient.component.html',
    styleUrls: ['./patient.style.css'],
    providers: [PatientService],
    encapsulation: ViewEncapsulation.None
})
export class PatientComponent implements OnInit {

    private model: Patient;
    private patients: Patient[];

    constructor(private patientService: PatientService) {
    }

    ngOnInit() {
        this.model = new Patient();

        this.patientService.getAllPatient().subscribe(data => {
            this.patients = data;
        });
    }

    private onSubmit() {
        this.patientService.addPatient(this.model).subscribe(data => {
            this.patients.push(data);
        });
    }

}
