
//tslint:disable
import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import { Headers } from '../admin/Headers';
import Tabulator from 'tabulator-tables';
import { DataService } from '../data-service.service';
import { Adresse } from './Adresse';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
declare var Tabulator: any;
declare var $: any;
declare var Swal: any;
@Component({
  selector: 'app-adminlieux',
  templateUrl: './adminlieux.component.html',
  styleUrls: ['./adminlieux.component.css']
})
export class AdminlieuxComponent implements OnInit, OnDestroy, AfterViewInit {
  tableData: Tabulator;
  dataLieux = []
  listRegions = []
  adresse = new Adresse();
  data = []
  villes = []
  angContactForm: FormGroup;
  angContactForm2: FormGroup;
  
  constructor(private service: DataService, private formBuilder: FormBuilder) {
    this.service.getLieux().subscribe(res=>this.villes= res)
    this.ajoutForm()
    this.updateForm()
    this.service.getRegion().subscribe(res=>{
      this.listRegions = res;
    })
    this.service.getAdresses().subscribe(res=>{
      this.data = res
      res.forEach(item=>{
        this.dataLieux.push({
          id: item.id,
          rue:item.rue,
          numero: item.numero,
          complement: item.complement,
          lat: item.lat,
          lng: item.lng,
          ville:item.lieu.label,
          postale: item.lieu.code_postal,
          region: item.lieu.region.label,
          delete:"deleteICO"
        })
      })
    this.createTableData(this.dataLieux)
    })
  }
  ngAfterViewInit(): void {
    
  }
  ngOnDestroy(): void {
    $("#lieux").removeClass("active")
  }

  ngOnInit(): void {
    $(".ui.dropdown").dropdown()
    $("#lieux").addClass("active")
  }
  showFormulaire(){
    $("#ajoutAdresse").modal('show')
  }
  deleteItem(row){

    Swal.fire({
      title: 'voulez vous supprimer cette Adresse ?',
      text: "Attention ! si l'adresse contient des appartements ils seront supprimé",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Oui, supprimer',
      cancelButtonText: 'non'
    }).then((result) => {
      if (result.value) {
        this.service.supprimerAdresse(row._cell.row.data.id).subscribe(res=>{
          Swal.fire(
            'Adresse supprimer',
            '',
            'success'
          )
          location.reload()
        })
      }
    })
  }
  createTableData(data){
    this.tableData = new Tabulator("#lieux-table", Headers.lieuxHeader(
      (e, row)=>{

        let id = row._cell.row.data.id
        let r = this.data.filter(i => i.id == id)
        this.adresse = r[0]
        if( row._cell.value == "deleteICO" ){
          this.deleteItem(row)
          return
        }
        this.angContactForm2.get("id").setValue(r[0].id)
        this.angContactForm2.get("numero").setValue(r[0].numero)
        this.angContactForm2.get("nom").setValue(r[0].rue)
        this.angContactForm2.get("complement").setValue(r[0].complement)
        this.angContactForm2.get("lat").setValue(r[0].lat)
        this.angContactForm2.get("lng").setValue(r[0].lng)
        this.angContactForm2.get("ville").setValue(r[0].lieu.id)
        $("#villesUp").dropdown('set selected', r[0].lieu.id)
        $("#updateAdresse").modal('show')
      }
    ))
    this.tableData.setData(data)
    this.tableData.setLocale("fr-fr");
  }

  ajoutForm(){
    this.angContactForm = this.formBuilder.group({
      numero:['', Validators.required],
      nom:['', Validators.required],
      complement:['', Validators.required],
      lat:['', Validators.required],
      lng:['', Validators.required],
      ville:['', Validators.required]
    })
  }
  updateForm(){
    this.angContactForm2 = this.formBuilder.group({
      id:[''],
      numero:['', Validators.required],
      nom:['', Validators.required],
      complement:['', Validators.required],
      lat:['', Validators.required],
      lng:['', Validators.required],
      ville:['', Validators.required]
    })
  }
  IDT(e){
    return {'error':this.angContactForm.controls[e].invalid &&
      ( this.angContactForm.controls[e].dirty || this.angContactForm.controls[e].touched )?true:false,
      'state':this.angContactForm.controls[e].errors}
  }
  saveAdresse(){
    let data = {
      rue: this.angContactForm.get("nom").value,
      numero: this.angContactForm.get("numero").value,
      complement: this.angContactForm.get("complement").value,
      lat: this.angContactForm.get("lat").value,
      lng: this.angContactForm.get("lng").value,
      lieu: {id:this.angContactForm.get("ville").value},
    }
    this.service.saveAdresses(data).subscribe(res=>{
      location.reload()
    })
  }
  updateAdresse(){
    let data = {
      id:this.angContactForm2.get("id").value,
      rue: this.angContactForm2.get("nom").value,
      numero: this.angContactForm2.get("numero").value,
      complement: this.angContactForm2.get("complement").value,
      lat: this.angContactForm2.get("lat").value,
      lng: this.angContactForm2.get("lng").value,
      lieu: {id:this.angContactForm2.get("ville").value}
    }
    this.service.updateAdresses(data).subscribe(res=>{
      $("#updateAdresse").modal('hide')
      location.reload()
    })
  }
}
