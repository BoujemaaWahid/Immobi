//tslint:disable
import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Headers } from '../admin/Headers';
import { DataService } from '../data-service.service';
declare var Tabulator: any;
declare var $: any;
declare var Swal: any;
@Component({
  selector: 'app-villesadmin',
  templateUrl: './villesadmin.component.html',
  styleUrls: ['./villesadmin.component.css']
})
export class VillesadminComponent implements OnInit, OnDestroy, AfterViewInit {
  tableData: any;
  data = []
  villes = []
  regions = []
  angContactForm: FormGroup;
  angContactForm2: FormGroup;

  constructor(private service: DataService, private formBuilder: FormBuilder) {
    this.ajoutContactForm()
    this.updateContactForm()
    this.service.getRegion().subscribe(res=>{
      this.regions = res
    })
    this.service.getLieux().subscribe(res=>{
      this.villes = res;
      res.forEach(item => {
        this.data.push({
          id: item.id,
          ville: item.label,
          postale: item.postal,
          region: item.region.label,
          region_id:item.region.id,
          delete:"deleteICO"
        })
      });
    this.createTableData(this.data)
    })
  }
  ngAfterViewInit(): void {
  }
  ngOnDestroy(): void {
    $("#villes").removeClass('active')
  }

  ngOnInit(): void {
    $(".ui.dropdown").dropdown()
    $("#villes").addClass('active')
  }
  ajoutContactForm(){
    this.angContactForm = this.formBuilder.group({
      label:['', Validators.required],
      postal:['', Validators.required],
      region:['', Validators.required]
    })
  }
  updateContactForm(){
    this.angContactForm2 = this.formBuilder.group({
      id:[''],
      label:['', Validators.required],
      postal:['', Validators.required],
      region:['', Validators.required]
    })
  }
  IDT(e){
    return {'error':this.angContactForm.controls[e].invalid &&
      ( this.angContactForm.controls[e].dirty || this.angContactForm.controls[e].touched )?true:false,
      'state':this.angContactForm.controls[e].errors}
  }
  showFormulaire(){
    $("#ajoutModal").modal('show')
  }


  deleteItem(row){

    Swal.fire({
      title: 'voulez vous supprimer cette ville ?',
      text: "Attention ! touts les données en liaison avec cette ville seront supprimé.",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Oui, supprimer',
      cancelButtonText: 'non'
    }).then((result) => {
      if (result.value) {
        this.service.supprimerLieu(row._cell.row.data.id).subscribe(res=>{
          Swal.fire(
            'Ville supprimer',
            '',
            'success'
          )
          location.reload()
        })
      }
    })
  }

  createTableData(data){
    this.tableData = new Tabulator("#villes-table",Headers.villesHeader(
      (e, cell)=>{
        console.log(cell)
        if( cell._cell.value == "deleteICO" ){
          this.deleteItem(cell)
          return;
        }
        this.angContactForm2.get("id").setValue(cell._cell.row.data.id)
        this.angContactForm2.get("label").setValue(cell._cell.row.data.ville)
        this.angContactForm2.get("postal").setValue(cell._cell.row.data.postale)
        this.angContactForm2.get("region").setValue(""+cell._cell.row.data.region_id)
        $("#region_field").dropdown('set selected', cell._cell.row.data.region_id)
        $("#updateModal").modal('show')
      }
    ))
    this.tableData.setData(data)
    this.tableData.setLocale("fr-fr");
  }
  saveLieu(){
    this.angContactForm.value.region = {id: this.angContactForm.value.region}
    console.log(this.angContactForm.value)
    this.service.saveLieux(this.angContactForm.value).subscribe(res=>{
      this.ajoutContactForm()
      $("#ajoutModal").modal('hide')
      location.reload()
    })
  }

  updateLieu(){
    this.angContactForm2.value.region = {id: this.angContactForm2.value.region}


    this.service.updateLieu(this.angContactForm2.value).subscribe(res=>{
      this.updateContactForm()
      $("#updateModal").modal('hide')
      location.reload()
    })
  }
}
