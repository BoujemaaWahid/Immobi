//tslint:disable
import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Headers } from '../admin/Headers';
import { DataService } from '../data-service.service';
declare var Tabulator: any;
declare var $: any;
declare var Swal: any;
@Component({
  selector: 'app-regionsadmin',
  templateUrl: './regionsadmin.component.html',
  styleUrls: ['./regionsadmin.component.css']
})
export class RegionsadminComponent implements OnInit, OnDestroy, AfterViewInit {
  tableData: any;
  angContactForm: FormGroup;
  angContactForm2: FormGroup;
  regionName = ""
  constructor(private service: DataService, private formBuilder: FormBuilder) { 
    this.createContactForm()
    this.updateContactForm()
    this.service.getRegion().subscribe(res=>{
      let data = []
      res.forEach(item=>{
        data.push({id: item.id, region: item.label})
      })
      console.log(data)
      this.createTableData(data)
    })
  }
  ngAfterViewInit(): void {
    
    this.createTableData(null)
  }
  ngOnDestroy(): void {
    $("#regions").removeClass("active")
    $(".modal").remove()
  }
  ngOnInit(): void {
    $("#regions").addClass("active")
    $(".ui.modal").modal()
  }
  showFormulaire(){
    $("#AM").modal('show')
  }
  
  createContactForm(){
    this.angContactForm = this.formBuilder.group({
      label:['', Validators.required]
    })
  }
  updateContactForm(){
    this.angContactForm2 = this.formBuilder.group({
      id:[''],
      label:['', Validators.required]
    })
  }

  IDT(e){
    return {'error':this.angContactForm.controls[e].invalid &&
      ( this.angContactForm.controls[e].dirty || this.angContactForm.controls[e].touched )?true:false,
      'state':this.angContactForm.controls[e].errors}
  }
  createTableData(data){
    this.tableData = new Tabulator("#regions-table", Headers.regionsHeader(
      (e, row)=>{
        this.updateContactForm()
        let id = row._cell.row.data.id
        let region = row._cell.row.data.region
        this.regionName = region
        this.angContactForm2.get("id").setValue(id)
        this.angContactForm2.get("label").setValue(region)
        $("#UM").modal('show')
      }
    ))
    this.tableData.setData(data)
  }

  ajoutRegion(){
    this.service.saveRegion(this.angContactForm.value).subscribe(res=>{
      $("#AM").modal('hide')
      location.reload()
    })
  }
  updateRegion(){
    this.service.updateRegion(this.angContactForm2.value).subscribe(res=>{
      $("#UM").modal('hide')
      location.reload()
    })
  }
  deleteRegion(){
    
    Swal.fire({
      title: 'voulez vous supprimer cette region ?',
      text: "Attention ! tous les données reliée à cette region seront supprimé.",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Oui, supprimer',
      cancelButtonText: 'non'
    }).then((result) => {
      if (result.value) {
        this.service.supprimerRegion(this.angContactForm2.get("id").value).subscribe(res=>{
          Swal.fire(
            'Region supprimer',
            '',
            'success'
          )
          location.reload()
        })
      }
    })
  }
}
