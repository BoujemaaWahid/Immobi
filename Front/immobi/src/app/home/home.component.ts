//tslint:disable
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { ContactForm } from './ContactezNous';
declare var $: any;
declare var AOS: any;
declare var HomeAnime: any;
declare var navBarActions: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  categoryContent = []
  basicSearchInputValue = ''
  angContactForm: FormGroup;
  contactFormButtonIsClicked = false;
  constructor(private formBuilder: FormBuilder) {
    this.createContactForm();
    this.categoryContent = [
    { category: 'Paris', description:"Description",image:'assets/logo.png', price:'4475.123$', title: 'Maison 1' },
  ];
  }

  sendContactFormMsg(){

  }
  HideConnectMsgInfo(){
    HomeAnime().hideConnectMsgInfo()
  }
  basicSearchClick(){
    HomeAnime().showConnectMsgInfo()
  }
  basicSearchButton($e){
    if( this.basicSearchInputValue == ''){
      HomeAnime().shake('#paramet')
      $e.preventDefault()
    }
  }
  createContactForm(){
    this.angContactForm = this.formBuilder.group({
      email:['', [Validators.required, Validators.email]],
      message:['', Validators.required]
    })
  }
  IDT(e){
    console.log( this.angContactForm.controls[e].errors );
    return {'error':this.angContactForm.controls[e].invalid &&
      ( this.angContactForm.controls[e].dirty || this.angContactForm.controls[e].touched )?true:false,
      'state':this.angContactForm.controls[e].errors}
  }
  ngOnInit(): void {
    HomeAnime().init()
    navBarActions().InitScrollInside()
    $('.ui.search')
    .search({
      type: 'category',
      searchFields   : [
        'title',
        'category',
        'price'
      ],
      source: this.categoryContent
    });
  }
}
