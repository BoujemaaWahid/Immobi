//tslint:disable
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../data-service.service';
declare var $: any;
declare var pulseAnimation: any;
declare var navBarActions: any;
import * as bcrypt from 'bcryptjs';
declare var Swal: any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  angContactForm: FormGroup;
  session = true;
  constructor(private data: DataService, private router: Router, private formBuilder: FormBuilder) {
    if( localStorage.getItem("idUser") != null ){
      if( localStorage.getItem("user_type") == "1")
      this.router.navigate(['/admin'])
    }
    this.createContactForm()
   }

  createContactForm(){
    this.angContactForm = this.formBuilder.group({
      id:['', Validators.required],
      pwd:['', Validators.required]
    })
  }

  IDT(e){
    return {'error':this.angContactForm.controls[e].invalid &&
      ( this.angContactForm.controls[e].dirty || this.angContactForm.controls[e].touched )?true:false,
      'state':this.angContactForm.controls[e].errors}
  }


  connect(){

    this.data.connectAccount({
      "fact_1": this.angContactForm.get("id").value,
      "fact_2": this.angContactForm.get("pwd").value
    }).subscribe(res=>{
      if( res['fact_1'] == null ){
        Swal.fire({
          icon: 'error',
          title: '',
          text: 'Verifiez votre identifiants'
        })
      }else{
        bcrypt.compare(this.angContactForm.get("pwd").value, res['fact_2'], (err, valid)=>{
          if( !valid ){
            Swal.fire({
              icon: 'error',
              title: '',
              text: 'Verifiez votre identifiants'
            })
          }else{
            localStorage.setItem("idUser", ""+res['fact_1'])
            if ( this.session ){ localStorage.setItem("keep_session", "true")}
            else { localStorage.setItem("keep_session", "false") }
            if( res['fact_3'] ){
              localStorage.setItem("user_type", "1")
              localStorage.setItem("authorize", "admin:0000"/*+this.angContactForm.get("pwd").value*/)
              this.router.navigate(['/admin'])
            }
            else localStorage.setItem("authorize", "member:member")
              //TO ACCOUNT
          }
        })
      }
    }, error=>{
      Swal.fire(
        'Internet',
        'Vous pouvez pas accéder à votre compte pour le moment',
        'info'
      )
    })
  }



  ngOnInit(): void {
    pulseAnimation();
    $("#baseMenu").css({'display':'flex'})
    $('#baseMenu').removeAttr('data-aos');
    $(".forServicesLink").css({'display':'none'})
    $(".forInformationsLink").css({'display':'none'})
  }

}
