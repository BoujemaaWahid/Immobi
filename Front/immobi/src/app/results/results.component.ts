//tslint:disable
import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
import { Filters } from './Filters';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { DataShare } from '../dataShare.service';
import { DataService } from '../data-service.service';
import { SocketmsgService } from '../socketmsg.service';


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
  gardeItems = false

  constructor(private conSub: SocketmsgService, private dataService: DataService,private dataShare: DataShare, private formBuilder: FormBuilder, private router: Router ) {
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
          this.processLocals(data)
          this.forResponsivity(data)
        })
      }
    }
    if( localStorage.getItem("user_type") ){
      if( localStorage.getItem("user_type") == "2" ) this.gardeItems = true
    }
  }
  ngOnDestroy(): void {
    localStorage.removeItem('basicFilter')
  }
  ngAfterViewInit(): void {
    $("#baseMenu").css({'display':'flex'})
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
      this.processLocals(data)
      localStorage.removeItem("basicFilter")
      localStorage.setItem("locales", JSON.stringify(this.locales))
      this.forResponsivity(data)
    })
  }
  processLocals(data){
    let id = localStorage.getItem("idUser")
    data.forEach(item => {
      item['garder'] = true
      let u = item.favoires.filter(e=> e.id == id)
      if( u.length > 0 )item['garder'] = false;
    });
    this.locales = data;
  }
  
  forResponsivity(data){
    if( data.length <= 1)this.grid_resp_class = "column"
    else if( data.length <= 2 )this.grid_resp_class = "seven wide column"
    else if( data.length <= 3) this.grid_resp_class = "five wide column"
    else this.grid_resp_class = "four wide column"
  }
  garderFavoir(id){
    this.dataService.mkFav({idu: localStorage.getItem("idUser"), idl:id}).subscribe(res=>{
      let item = JSON.parse( localStorage.getItem("favoires") )
      item.push( this.locales.filter(e=> e.id == id)[0])
      localStorage.setItem("favoires", JSON.stringify(item))
      this.locales.map(e=> (e.id == id)?e.garder = false: e.garder = e.garder )
      this.conSub.conSub.next({update:true})
    })
  }
  seeDetails(id){
    let e = this.locales.find(item => item.id == id)
    this.dataShare.changeMessage(e)
    this.router.navigate(['details']);
  }
}
