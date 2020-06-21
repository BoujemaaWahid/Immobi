//tslint:disable
import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
import { Filters } from './Filters';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { DataShare } from '../dataShare.service';
declare var $: any;
declare var basicInit: any;
@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit, AfterViewInit, OnDestroy {
  filter: Filters;
  constructor(private dataShare: DataShare, private formBuilder: FormBuilder, private router: Router ) {
    this.filter = new Filters()
    try{
      let bf = JSON.parse(localStorage.getItem('basicFilter'))
      this.filter.budget.max_budget = bf['max_budget']
      this.filter.lieux.villes.push("AL")
      this.filter.projets.acheter_bien = (bf['projet'] == '2')
      this.filter.projets.louer_bien = (bf['projet'] == '1')
    }catch(exception){}
  }
  ngOnDestroy(): void {
    localStorage.removeItem('basicFilter')
  }
  ngAfterViewInit(): void {}

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
  budgetProc(){}

  seeDetails(item){
    this.dataShare.changeMessage({
      id: 5,
      price: 447.2,
      name: "Villa"
    })
    this.router.navigate(['details']);
  }

}
