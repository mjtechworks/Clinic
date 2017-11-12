import {Component, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styles: [],
  encapsulation: ViewEncapsulation.None
})
export class DoctorComponent implements OnInit {

  newDoctor(fullName, email) {
    alert('Doctor : ' + fullName + ' with email : ' + email + " was created ! ");
  }

  constructor() {
  }

  ngOnInit() {
  }

}
