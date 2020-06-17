//tslint:disable
import { Component, OnInit } from '@angular/core';
declare var $: any;
declare var basicInit: any;
@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {
  x = []
  constructor() {
    for(let cmp = 0; cmp < 100 ; cmp++){
      this.x.push(cmp)
    }
  }

  ngOnInit(): void {
    basicInit();

  }
  budgetProc(){
    alert()
  }

}
