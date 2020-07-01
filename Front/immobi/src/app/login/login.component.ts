//tslint:disable
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../data-service.service';
declare var $: any;
declare var pulseAnimation: any;
declare var navBarActions: any;
import * as bcrypt from 'bcryptjs';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  angContactForm: FormGroup;
  error_identifiants = false;
  session = true;
  constructor(private data: DataService, private router: Router, private formBuilder: FormBuilder) {
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
        this.error_identifiants = true;
        setTimeout(()=>{this.error_identifiants = false;}, 2500)
      }else{
        console.log("ok")
        bcrypt.compare(this.angContactForm.get("pwd").value, res['fact_2'], (err, valid)=>{
          if( !valid ){
            this.error_identifiants = true;
            setTimeout(()=>{this.error_identifiants = false;}, 2500)
          }else{
            localStorage.setItem("idUser", ""+res['fact_1'])
            if ( this.session ){ localStorage.setItem("keep_session", "true")}
            else { localStorage.setItem("keep_session", "false") }
            if( res['fact_3'] )localStorage.setItem("authorize", "admin:"+res['fact_2'])
            else localStorage.setItem("authorize", "member:member")
              //TO ACCOUNT
          }
        })
      }
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
