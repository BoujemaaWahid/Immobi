//tslint:disable
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Viewer from 'viewerjs';
import { DataShare } from '../dataShare.service';

declare var $: any;
@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit, AfterViewInit {
  center: any;
  markers: Array<any>;
  angContactForm: FormGroup;
  gallery: Viewer;
  item: any;
  constructor(private dataShare: DataShare, private formBuilder: FormBuilder) {
    this.dataShare.currentMessage.subscribe(item => {

      if( item == null ) this.item = JSON.parse ( localStorage.getItem("details") )
      else {
        this.item = item
        localStorage.setItem("details", JSON.stringify(item))
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
        lat: 48.859874,
        lng: 2.310024,
      },
      title: this.item['adresse']['numero'] + " " + this.item['adresse']['rue'] + " " + this.item['adresse']['lieu']['label'],
      options: { animation: google.maps.Animation.BOUNCE },
    });
    var marker = new google.maps.Marker({
      position: {
        lat: 48.891250,
        lng: 2.238387,
      },
      title: 'Hello World',
      icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|ddd'
  });
    this.markers.push(marker);
  }
  ngAfterViewInit(): void {
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

}
