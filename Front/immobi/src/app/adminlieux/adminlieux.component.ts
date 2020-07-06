
//tslint:disable
import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import { Headers } from '../admin/Headers';
import Tabulator from 'tabulator-tables';
import { DataService } from '../data-service.service';
import { isNgTemplate } from '@angular/compiler';
import { Adresse } from './Adresse';
declare var Tabulator: any;
declare var $: any;
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
  constructor(private service: DataService) {
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
          region: item.lieu.region.label
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
  showFormulaire(){}

  createTableData(data){
    this.tableData = new Tabulator("#lieux-table", Headers.lieuxHeader(
      (e, row)=>{
        let id = row._cell.row.data.id
        let r = this.data.filter(i => i.id == id)
        this.adresse = r[0]
        $("#updateAdresse").modal('show')
      }
    ))
    this.tableData.setData(data)
    this.tableData.setLocale("fr-fr");
  }
}
