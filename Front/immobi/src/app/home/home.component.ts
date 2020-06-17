//tslint:disable
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
declare var $: any;
declare var AOS: any;
declare var pulseAnimation: any;
declare var initAos:any;
declare var HomeAnime: any;
declare var navBarActions: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {

  angContactForm: FormGroup;
  contactFormButtonIsClicked = false;
  constructor(private formBuilder: FormBuilder, private router: Router) {
    this.createContactForm();
  }
  ngAfterViewInit(): void {
    $(".dropdown").dropdown()
  }

  sendContactFormMsg(){

  }
  HideConnectMsgInfo(){
    HomeAnime().hideConnectMsgInfo()
  }
  basicSearchClick(){
    HomeAnime().showConnectMsgInfo()
  }
  process(){
    this.router.navigate(['recherche'])
  }
  createContactForm(){
    this.angContactForm = this.formBuilder.group({
      email:['', [Validators.required, Validators.email]],
      message:['', Validators.required]
    })
  }
  IDT(e){
    return {'error':this.angContactForm.controls[e].invalid &&
      ( this.angContactForm.controls[e].dirty || this.angContactForm.controls[e].touched )?true:false,
      'state':this.angContactForm.controls[e].errors}
  }
  ngOnInit(): void {
    pulseAnimation();
    initAos();
    HomeAnime().init()
    navBarActions().InitScrollInside()
  }
}
