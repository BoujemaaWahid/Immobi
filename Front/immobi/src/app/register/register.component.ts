//tslint:disable
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DataService } from '../data-service.service';
import * as bcrypt from 'bcryptjs';
declare var $: any;
declare var pulseAnimation: any;
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  angContactForm: FormGroup;
  username_exist = false;
  email_exist = false;
  tel_exist = false;

  constructor(private services: DataService,private formBuilder: FormBuilder) { this.createContactForm()}

  createContactForm(){
    this.angContactForm = this.formBuilder.group({
      email:['', [Validators.required, Validators.email]],
      phone:['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      password:['', [Validators.required, Validators.minLength(6)]],
      username:['', Validators.required]
    })
  }

  ngOnInit(): void {
    pulseAnimation();
    $('#baseMenu').removeAttr('data-aos');
    $(".forServicesLink").css({'display':'none'})
    $(".forInformationsLink").css({'display':'none'})
  }

  IDT(e){
    return {'error':this.angContactForm.controls[e].invalid &&
      ( this.angContactForm.controls[e].dirty || this.angContactForm.controls[e].touched )?true:false,
      'state':this.angContactForm.controls[e].errors}
  }

  isEmailExist(){
    let email = this.angContactForm.get("email").value
    //this.email_exist = true
    //this.services.isThere(1, email).subscribe(res=> this.email_exist = res)
  }
  isTelExist(){
    let phone = this.angContactForm.get("phone").value
    this.tel_exist = true
    this.services.isThere(3, phone).subscribe(res=> this.tel_exist = res)
  }
  isNameExist(){
    let username = this.angContactForm.get("username").value
    this.username_exist = true
    this.services.isThere(2, username).subscribe(res=> this.username_exist = res)
  }
  save(){
    const salt = bcrypt.genSaltSync(10);
    let data = {
      "username": this.angContactForm.get("username").value,
      "telephone": this.angContactForm.get("phone").value,
      "password": bcrypt.hashSync(this.angContactForm.get("password").value, salt),
      "email": this.angContactForm.get("email").value
    }
    localStorage.setItem("register", JSON.stringify(data) );
    console.log("ok")
    this.services.compteValidation(data.email).subscribe(res=>{
      console.log("RES0", res)
    })
  }
}
