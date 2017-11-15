import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Patient} from "./patient";

@Component({
    selector: 'app-doctor',
    templateUrl: './patient.component.html',
    styles: [],
    encapsulation: ViewEncapsulation.None
})
export class DoctorComponent implements OnInit {

    private model = new Patient();

    constructor() {
    }

    ngOnInit() {
    }

    private onSubmit() {
        console.log(this.model);
    }

}
