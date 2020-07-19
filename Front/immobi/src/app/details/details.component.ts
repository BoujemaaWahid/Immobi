//tslint:disable
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Viewer from 'viewerjs';
import { DataShare } from '../dataShare.service';
import { Deactivator } from '../DeacGuard';
import { DataService } from '../data-service.service';
declare var Swal: any;
declare var $: any;
@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit, AfterViewInit, Deactivator {
  center: any;
  markers: Array<any>;
  angContactForm: FormGroup;
  gallery: Viewer;
  item: any;
  constructor(private services: DataService, private dataShare: DataShare, private formBuilder: FormBuilder) {
    this.dataShare.currentMessage.subscribe(item => {
      if( item == null ) this.item = JSON.parse ( localStorage.getItem("details") )
      else {
        this.item = item
      }
    })
    this.markers = new Array<any>();
    this.createContactForm();
    navigator.geolocation.getCurrentPosition(position => {
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      }
    })
    this.markers.push({
      position: {
        lat: this.item['adresse']['latitude'],
        lng: this.item['adresse']['longitude'],
      },
      title: this.item['adresse']['numero'] + " " + this.item['adresse']['rue'] + " " + this.item['adresse']['lieu']['label'],
      options: { animation: google.maps.Animation.BOUNCE },
    });
    var marker = new google.maps.Marker({
      position: this.center,
      title: 'Hello World',
      icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|ddd'
    });
    this.markers.push(marker);
  }
  async confirm(): Promise<boolean> {
    if( this.angContactForm.dirty )
      return await this.exitPage()
    return true;
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

  ngAfterViewInit(): void {
    $("#baseMenu").css({'display':'flex'})
    this.gallery = new Viewer(document.getElementById("gallery"))
    this.gallery.show()
  }

  ngOnInit(): void {
    $('#baseMenu').removeAttr('data-aos');

    $(".forServicesLink").css({display:'none'})
    $(".forInformationsLink").css({display:'none'})
  }
  createContactForm(){
    this.angContactForm = this.formBuilder.group({
      
      email:['', [Validators.required, Validators.email]],
      phone:['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      message:['', Validators.required]
    })
  }
  IDT(e){
    return {'error':this.angContactForm.controls[e].invalid &&
      ( this.angContactForm.controls[e].dirty || this.angContactForm.controls[e].touched )?true:false,
      'state':this.angContactForm.controls[e].errors}
  }
  showGalleryAt(index){
    for(let cmp = 0; cmp < index; cmp++){
      this.gallery.next(true)
    }
    this.gallery.show()
  }

  sendMessage(){
    let data = {
      local: { id: this.item['id'] },
      email: this.angContactForm.get("email").value,
      tel: this.angContactForm.get("phone").value,
      message: this.angContactForm.get("message").value
    }
    this.services.saveDemande(data).subscribe(res=>{
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
