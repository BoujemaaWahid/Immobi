//tslint:disable
import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import Tabulator from 'tabulator-tables';
import { Headers } from '../admin/Headers';
import { Local } from './local.entity';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
declare var $: any;
@Component({
  selector: 'app-localadmin',
  templateUrl: './localadmin.component.html',
  styleUrls: ['./localadmin.component.css']
})
export class LocaladminComponent implements OnInit, OnDestroy, AfterViewInit {
  local: Local
  images = []
  idGenImage = 0;
  angContactForm: FormGroup;
  constructor( private formBuilder: FormBuilder) {
    this.local = new Local()
    this.createContactForm()
  }
  ngAfterViewInit(): void {
    $('.special.cards .image').dimmer({
      on: 'hover'
    });
    $(".ui.dropdown").dropdown()
    $(".modal").modal({allowMultiple: true})
    $(".gallery").click(()=>{
      $(".small.modal").modal('show')
    })
  }

  ngOnDestroy(): void {
    $("#locales").removeClass("active")
  }
  showFormulaire(){

    $("#ajoutModal").modal('show')
  }

  createContactForm(){
    this.angContactForm = this.formBuilder.group({
      titre:['', Validators.required],
      prix:['', Validators.required],
      adresse:['', Validators.required],
      date:['', Validators.required],
      surface:['', Validators.required],
      terrain:['', Validators.required],
      chambres:['', Validators.required],
      pieces:['', Validators.required],
      description:['', Validators.required],
      types:[''],
      images:[''],
      disponible:['', Validators.required],
      projet:['', Validators.required]
    })
  }

  IDT(e){
    return {'error':this.angContactForm.controls[e].invalid &&
      ( this.angContactForm.controls[e].dirty || this.angContactForm.controls[e].touched )?true:false,
      'state':this.angContactForm.controls[e].errors}
  }

  ngOnInit(): void {
    $("#locales").addClass("active")
    this.images = []
    let table = new Tabulator("#locales-table", Headers.localHeader(
      (e, cell)=>{
        this.local.id = cell._cell.row.data.id
        this.local.titre = cell._cell.row.data.titre
        this.local.chambres = cell._cell.row.data.chambres
        this.local.pieces = cell._cell.row.data.pieces
        this.local.prix = cell._cell.row.data.prix
        this.local.surface = cell._cell.row.data.surface
        this.local.surface_terrain = cell._cell.row.data.terrain
        this.local.description = cell._cell.row.data.desc
        this.local.disponible = cell._cell.row.data.dispo
        this.local.date_pub = cell._cell.row.data.pub
        this.local.adresse = cell._cell.row.data.idadr
        this.local.type = cell._cell.row.data.type
        console.log(this.local)
        $("#adresses_locales").dropdown('set selected', this.local.adresse)
        $("#types").dropdown('set selected', this.local.type)
        $(".fullscreen.modal").modal('show')
      }
    ));
    table.setData(this.data())
    table.setLocale("fr-fr");
  }

  detache(id){
    if( id.indexOf('new') != -1 ){
      this.images = this.images.filter(i => {
        return i.id != id
      })
      return
    }
    if ( this.local.images != null ){
      this.local.images = this.images.filter(i=>{
        return i.id != id
      })
    }
  }
  fileChange(event){

      let fileList = event.target.files;
      for(let cmp = 0; cmp < fileList.length; cmp++){
        let reader = new FileReader()
        reader.onloadend = () => {
          this.idGenImage += 1
          this.images.push({id:'new'+this.idGenImage, src:reader.result})
        }
        reader.readAsDataURL(fileList[cmp])
    }
  }
  data(){

    return [
      {id:1, titre:"maison/villa", pub:"2020-03-07", desc:"DESCRIPTION", dispo:true,
      chambres:5, pieces:3, prix:456, achat:"loue", surface:250, terrain:100,
      type:["1","2"],
      idadr:1, adresse:"67 rue de courbevoi", ville:"Nanterre", region: "paris"
      },

      {id:2, titre:"maison/villa", pub:"2020-03-07", desc:"DESCRIPTION", dispo:true,
      chambres:5, pieces:3, prix:456, achat:"loue", surface:250, terrain:100,
      idadr:2, adresse:"67 rue de courbevoi", ville:"Nanterre", region: "paris"
      },

      {id:3, titre:"maison/villa", pub:"2020-03-07", desc:"DESCRIPTION", dispo:true,
      chambres:5, pieces:3, prix:456, achat:"achat", surface:250, terrain:100,
      adresse:"67 rue de courbevoi", ville:"Nanterre", region: "paris"
      },


      {id:4, titre:"maison/villa", pub:"2020-03-07", desc:"DESCRIPTION", dispo:true,
      chambres:5, pieces:3, prix:456, achat:"achat", surface:"250 ", terrain:100,
      adresse:"67 rue de courbevoi", ville:"Nanterre", region: "paris"
      }
    ]
  }
}
