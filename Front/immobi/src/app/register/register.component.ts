//tslint:disable
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DataService } from '../data-service.service';
import * as bcrypt from 'bcryptjs';
import { fromEvent, Observable } from 'rxjs';
declare var $: any;
declare var pulseAnimation: any;
declare var Swal: any;
import { debounceTime, map, tap } from 'rxjs/operators';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {
  angContactForm: FormGroup;
  username_exist = false;
  email_exist = false;
  tel_exist = false;

  constructor(private services: DataService,private formBuilder: FormBuilder) { this.createContactForm()}
  ngOnDestroy(): void {
    
    $("#forRegister").removeClass("active")
    $("#forRegister").removeClass("basicActiveLinkref")
  }

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
    
    $("#forRegister").addClass("active")
    $("#forRegister").addClass("basicActiveLinkref")

    this.angContactForm.get("username").valueChanges.pipe(
      debounceTime(2000),
      tap(val=>{
        this.username_exist = true
        this.services.isThere(2, val).subscribe(res=> this.username_exist = res)
      })
    ).subscribe()
    this.angContactForm.get("email").valueChanges.pipe(
      debounceTime(2000),
      tap(val=>{
        this.email_exist = true
        this.services.isThere(1, val).subscribe(res=> this.email_exist = res)
      })
    ).subscribe()
    this.angContactForm.get("phone").valueChanges.pipe(
      debounceTime(2000),
      tap(val=>{
        this.tel_exist = true
        this.services.isThere(3, val).subscribe(res=> this.tel_exist = res)
      })
    ).subscribe()
  }

  IDT(e){
    return {'error':this.angContactForm.controls[e].invalid &&
      ( this.angContactForm.controls[e].dirty || this.angContactForm.controls[e].touched )?true:false,
      'state':this.angContactForm.controls[e].errors}
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
    const smsg = new Observable(observer=>{
      observer.next(
        Swal.fire({
          position: 'top-end',
          icon: 'info',
          title: 'La préparation de votre compte est en cours.',
          showConfirmButton: false,
          timer: 2000
        })
      )
    }).subscribe()

    this.services.compteValidation(data.email).subscribe(res=>{
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Un email vous était envoyé pour confirmer votre adresse',
        showConfirmButton: false,
        timer: 3500
      })
    }, error=>{
      Swal.fire(
        'Internet',
        'Vous pouvez pas enregistrer votre compte pour le moment',
        'info'
      )
    })
  }
}
