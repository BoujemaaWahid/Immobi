//tslint:disable
import { Component, OnInit, HostBinding, HostListener } from '@angular/core';
import { Router } from '@angular/router';
declare var $: any;
declare var navBarHover: any;
declare var navBarActions: any;
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  showFilter = !(localStorage.getItem('forSideBar') == null)
  display = {
    disp: !localStorage.getItem("idUser")
  }
  constructor(private route: Router) {
  }
  account(){
    let tp = localStorage.getItem("user_type")
    if( tp == '1') this.route.navigate(['admin'])
  }
  ngOnInit(): void {
    navBarActions().updateActiveLink()
    navBarActions().hmUpdateActiveLink()

  }
  showSideMenu(){
    navBarActions().navAnimation()
  }

}
