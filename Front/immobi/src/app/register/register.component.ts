import { Component, OnInit } from '@angular/core';
declare var $: any;
declare var pulseAnimation: any;
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    pulseAnimation();
    $('#baseMenu').removeAttr('data-aos');
  }

}
