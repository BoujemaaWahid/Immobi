//tslint:disable
import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import Tabulator from 'tabulator-tables';
import { Headers } from '../admin/Headers';
import { Local } from './local.entity';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DataService } from '../data-service.service';
import { ActivatedRoute, Router } from '@angular/router';
declare var Swal: any;
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
  listLocals = []
  listAdresses = []
  listTypes = []
  tableData: Tabulator;
  constructor(private arouter: Router, private services: DataService,  private formBuilder: FormBuilder) {
    this.local = new Local()
    this.createContactForm()
    this.services.getLocals().subscribe(res=>{
      this.services.adresses().subscribe(adr=>{
        adr.forEach(item => {
          this.listAdresses.push({
            id: item.id,
            adresse: item.numero+" "+item.rue+" "+item.lieu.label+" "+item.lieu.code_postal
          })
        });
      })


      this.services.getTypes().subscribe(adr=>{
        adr.forEach(item => {
          this.listTypes.push({
            id: item.id,
            label: item.label
          })
        });
      })




      this.listLocals = []
      res.forEach(item => {
        this.listLocals.push({
          id:item.id,
          titre:item.titre,
          pub: item.date_pub,
          desc: item.description,
          dispo: item.disponible,
          chambres: item.chambres,
          pieces: item.pieces,
          prix: item.prix,
          achat: item.projet?'achat':'loue',
          surface: item.surface,
          terrain: item.surface_terrain,
          type:item.types,
          images: item.images,
          idadr: item.adresse,
          adresse: item.adresse.numero+" "+item.adresse.rue,
          ville: item.adresse.lieu.label,
          postal: item.adresse.lieu.code_postal,
          delete:"deleteICO"
        })
      });
      this.createTableData(this.listLocals)
    })
  }

  ngAfterViewInit(): void {
    $('.special.cards .image').dimmer({
      on: 'hover'
    });
    $(".ui.dropdown").dropdown()
    $("#types").dropdown()
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
  }

  createTableData(data){
    this.tableData = new Tabulator("#locales-table", Headers.localHeader(
      (e, cell)=>{
        if( cell._cell.value == "deleteICO" ){


          Swal.fire({
            title: 'voulez vous supprimer ce local ?',
            text: "Vous ne pourrez pas revenir en arrière !",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Oui, supprimer',
            cancelButtonText: 'non'
          }).then((result) => {
            if (result.value) {
              this.services.supprimerLocal(cell._cell.row.data.id).subscribe(res=>{
                Swal.fire(
                  'Local supprimer',
                  '',
                  'success'
                )
                location.reload()
              })
            }
          })
          return;
        }
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

        this.local.images = cell._cell.row.data.images
        $("#adresses_locales").dropdown('set selected', this.local.adresse['id'])
        let t = []
        this.local.type.forEach(element => {
          t.push(element.label)
        });
        $("#types").dropdown('set selected', t)
        $("#updateModal").modal('show')
      }
    ));
    this.tableData.setData(data)
    this.tableData.setLocale("fr-fr");
  }


  detache(id){
    try{
      if( id.indexOf('new') != -1 ){
        this.images = this.images.filter(i => {
          return i.id != id
        })
        return
      }
    }catch(e){
      if ( this.local.images != null ){
        this.local.images = this.local.images.filter(i=>{
          return i.id != id
        })
      }
    }
  }



  saveLocal(){
    let t = []
    this.angContactForm.get("types").value.forEach(item => {
      t.push({id: item})
    });
    let data = {
      "adresse":{"id": this.angContactForm.get("adresse").value},
      "chambres":this.angContactForm.get("chambres").value,
      "pieces": this.angContactForm.get("pieces").value,
      "titre": this.angContactForm.get("titre").value,
      "description": this.angContactForm.get("description").value,
      "prix": this.angContactForm.get("prix").value,
      "types":t,
      "surface": this.angContactForm.get("surface").value,
      "surface_terrain": this.angContactForm.get("terrain").value,
      "projet":this.angContactForm.get("projet").value,
      "date_pub": this.angContactForm.get("date").value,
      "disponible": this.angContactForm.get("disponible").value,
    }
    this.services.saveLocal(data).subscribe(res=>{
      if( res != 0 && this.images.length > 0){
         let imgs = []
          this.images.forEach(item=>{
            imgs.push({"base64": item.src, "local":{id: res}})
          })
          $("#ajoutModal").modal('hide')
          this.services.saveImages(imgs).subscribe(res=>{
          this.images = []
          })
          location.reload()
      }else{
        location.reload()
      }
    })
  }








  updateLocal(){
    if( this.images.length > 0 ){
      let imgs = []
      this.images.forEach(item=>{
        imgs.push({"base64": item.src, "local":{id: this.local.id}})
      })
      this.services.saveImages(imgs).subscribe(res=>{
      this.images = []
      })
    }
    let t =[]
    this.local.type.forEach(item=>{
      this.listTypes.forEach(item2=>{
        if( item == item2.label )
        t.push({id: item2.id})
      })
    })
    this.local["types"] = t
    delete this.local['type']
    this.services.updateLocal(this.local).subscribe(res=>{
      console.log(res)
      $("#updateModal").modal('hide')
      location.reload()
    })
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

  filter = []
  setFilter(){
    try{
      this.tableData.removeFilter(this.filter[0], this.filter[1], this.filter[2])
      this.filter = []
    }catch(e){}
    this.filter.push($("#elementf").val())
    this.filter.push($("#operatorf").dropdown('get value'))
    this.filter.push($("#valuef").val())
    if(this.filter[0] != "" && this.filter[1] != "" && this.filter[2] != ""){
      this.tableData.setFilter(this.filter[0], this.filter[1],this.filter[2])
    }
  }
}
