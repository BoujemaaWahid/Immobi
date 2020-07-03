//tslint:disable
import { Component, OnInit } from '@angular/core';
import { Headers } from './Headers';
import { Router, ActivatedRoute } from '@angular/router';

declare var Tabulator: any;
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private route: Router, private aroute: ActivatedRoute) {

   }

  ngOnInit(): void {
    this.route.navigate(['locales'], {relativeTo: this.aroute})
  }


}
