//tslint:disable
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
declare var $: any;
@Component({
  selector: 'app-adminmenu',
  templateUrl: './adminmenu.component.html',
  styleUrls: ['./adminmenu.component.css']
})
export class AdminmenuComponent implements OnInit {

  constructor(private route: Router, private ar: ActivatedRoute) { }

  ngOnInit(): void {
    $('#baseMenu').css({display: 'none'})
  }
  locales(){
    this.route.navigate(['locales'], {relativeTo: this.ar})
  }


}
