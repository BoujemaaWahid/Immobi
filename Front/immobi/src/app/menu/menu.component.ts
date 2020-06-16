//tslint:disable
import { Component, OnInit, HostBinding, HostListener } from '@angular/core';
declare var $: any;
declare var navBarHover: any;
declare var navBarActions: any;
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    navBarActions().updateActiveLink()
    navBarActions().hmUpdateActiveLink()

  }
  showSideMenu(){
    navBarActions().navAnimation()
  }


}
