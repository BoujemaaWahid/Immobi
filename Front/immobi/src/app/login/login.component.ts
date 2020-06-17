//tslint:disable
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
declare var $: any;
declare var pulseAnimation: any;
declare var navBarActions: any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
    pulseAnimation();

    $('#baseMenu').removeAttr('data-aos');
  }

}
