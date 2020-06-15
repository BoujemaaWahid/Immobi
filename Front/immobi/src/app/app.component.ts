//tslint:disable
import { Component, AfterViewInit, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
declare var $: any;
declare var navBarHover: any;
declare var navBarActions: any;
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit{
  ngAfterViewInit(): void {

  }
  ngOnInit(): void {
    navBarHover();
    navBarActions().InitScrollInside();
  }
  showSideMenu(){
    navBarActions().navAnimation()
  }
  prepareRoute(outlet: RouterOutlet) {
    return null;
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation']
  }
}
