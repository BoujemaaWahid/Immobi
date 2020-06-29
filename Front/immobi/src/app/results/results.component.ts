//tslint:disable
import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
import { Filters } from './Filters';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { DataShare } from '../dataShare.service';
import { DataService } from '../data-service.service';


declare var $: any;
declare var basicInit: any;
@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit, AfterViewInit, OnDestroy {
  filter: Filters;
  listLieuxRegions = []
  locales = []
  grid_resp_class = ""


  constructor(private dataService: DataService,private dataShare: DataShare, private formBuilder: FormBuilder, private router: Router ) {
    this.filter = new Filters()

    try{
      this.listLieuxRegions = JSON.parse(localStorage.getItem("villes"))
      let bf = JSON.parse(localStorage.getItem('basicFilter'))

      this.filter.budget.max_budget = bf['max_budget']
      this.filter.lieux.villes = bf['villes']
      this.filter.projets.acheter_bien = (bf['projet'] == '2')
      this.filter.projets.louer_bien = (bf['projet'] == '1')
      this.findLocalesWith()
    }catch(exception){
      try{
        this.locales = JSON.parse(localStorage.get("locales"))
      }catch(ex){
        this.dataService.getLocals().subscribe(data=>{
          this.locales = data
          this.forResponsivity(data)
        })
      }
    }
  }
  ngOnDestroy(): void {
    localStorage.removeItem('basicFilter')
  }
  ngAfterViewInit(): void {

  }

  setTempTransport(item, isTemp): void{
    if (isTemp)this.filter.lieux.temp = item
    else this.filter.lieux.transport = item
    this.filter.surface.max_surface_habitable
  }

  togglePieces(item, targets){
    Object.keys(this.filter.pieces).forEach( (key)=> {
        if( key.indexOf(targets) != -1 ){
          this.filter.pieces[key] = false
        }
    })
    this.filter.pieces[item] = true
  }
  ngOnInit(): void {
    basicInit();
  }
  findLocalesWith(){
    this.dataService.getLocalsWithFilters(this.filter.prepare()).subscribe(data => {
      this.locales = data
      localStorage.removeItem("basicFilter")
      localStorage.set("locales", JSON.stringify(data))
      this.forResponsivity(data)
    })
  }
  forResponsivity(data){
    if( data.length <= 1)this.grid_resp_class = "column"
    else if( data.length <= 2 )this.grid_resp_class = "seven wide column"
    else if( data.length <= 3) this.grid_resp_class = "five wide column"
    else this.grid_resp_class = "four wide column"
  }
  seeDetails(id){
    let e = this.locales.find(item => item.id == id)
    console.log(e)
    this.dataShare.changeMessage(e)
    this.router.navigate(['details']);
  }
}
