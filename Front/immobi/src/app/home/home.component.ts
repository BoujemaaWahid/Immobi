//tslint:disable
import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { BasicFilter } from './BasicFilter';
import { DataShare } from '../dataShare.service';
import { DataService } from '../data-service.service';
import { LieuxRegions } from '../models/LieuxRegions';
import { Deactivator } from '../DeacGuard';
declare var $: any;
declare var AOS: any;
declare var pulseAnimation: any;
declare var initAos:any;
declare var HomeAnime: any;
declare var navBarActions: any;
declare var Swal: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit, OnDestroy, Deactivator {

  angContactForm: FormGroup;
  contactFormButtonIsClicked = false;
  basicFilter: BasicFilter;
  listLieuxRegions = new Array<LieuxRegions>();

  constructor(private dataService: DataService, private dataShare: DataShare, private formBuilder: FormBuilder, private router: Router) {
    this.createContactForm();
    this.basicFilter = new BasicFilter();
    try{ localStorage.removeItem("basicFilter") }catch(e){}
    this.dataService.getLieuxEtRegions().subscribe(data => {
      if( data instanceof Array){
        data.forEach(item=>{
          this.listLieuxRegions.push(item)
          localStorage.setItem("villes", JSON.stringify(this.listLieuxRegions))
        })
      }
    })
  }
  async confirm(): Promise<boolean> {
    if( this.angContactForm.dirty )
      return await this.exitPage();
    else return true;
  }

  exitPage(): Promise<boolean> {
    return new Promise((resolve) => {
      Swal.fire({
        title: 'Vous voulez quitter la page ?',
        text: "Vous vouliez envoyé un message, souhaitez vous continuer ?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'je reste',
        cancelButtonText: 'je quitte'
      }).then((result) => {
        if (result.value) {
          resolve(false)
        }
        resolve(true)
      })
    })
  }
  ngOnDestroy(): void {
      $("#forRoot").removeClass("active")
      $("#forRoot").removeClass("basicActiveLinkref")
  }

  ngAfterViewInit(): void {$("#baseMenu").css({'display':'flex'})}

  sendContactFormMsg(){
  }
  HideConnectMsgInfo(){
    HomeAnime().hideConnectMsgInfo()
  }
  basicSearchClick(){
    HomeAnime().showConnectMsgInfo()
  }
  process(){
    localStorage.setItem('basicFilter', JSON.stringify(this.basicFilter))

    navBarActions().forRecherche()
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

  sendMessage(){
    let data = {
      email: this.angContactForm.get("email").value,
      message: this.angContactForm.get("message").value
    }
    this.dataService.saveMessage(data).subscribe(res=>{
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Votre message etait envoyé.',
        showConfirmButton: false,
        timer: 1100
      })
    })
  }

}
