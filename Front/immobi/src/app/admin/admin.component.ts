//tslint:disable
import { Component, OnInit } from '@angular/core';
import { Headers } from './Headers';
import { Router, ActivatedRoute } from '@angular/router';
declare var $: any;
declare var Tabulator: any;
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private route: Router, private aroute: ActivatedRoute) {
    if( localStorage.getItem("idUser") == null ){
      this.route.navigate(['/login'])
    }else if( localStorage.getItem("user_type") != "1" ){this.route.navigate(['/login'])}
   }

  ngOnInit(): void {
    $("#baseMenu").css({display:'none'})
  }
  adresses(){
    this.route.navigate(['adresses'], {relativeTo: this.aroute})
  }
  locales(){
    this.route.navigate(['locales'], {relativeTo: this.aroute})
  }
  villes(){
    this.route.navigate(['villes'], {relativeTo: this.aroute})
  }
}
