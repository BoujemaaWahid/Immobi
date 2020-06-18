//tslint:disable
import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
import { Filters } from './Filters';
import { FormGroup, FormBuilder } from '@angular/forms';
declare var $: any;
declare var basicInit: any;
@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit, AfterViewInit {
  filter: Filters;
  constructor(private formBuilder: FormBuilder ) {
    this.filter = new Filters()
    try{
    let bf = JSON.parse(localStorage.getItem('basicFilter'))
    this.filter.budget.max_budget = bf['max_budget']
    this.filter.lieux.villes.push("AL")
    this.filter.projets.acheter_bien = (bf['projet'] == '2')
    this.filter.projets.louer_bien = (bf['projet'] == '1')
    }catch(exception){}
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
  budgetProc(){


  }

}
