//tslint:disable
import { Component, AfterViewInit, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
declare var $: any;
declare var navBarHover: any;
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  inCurrentItem = false
  ngOnInit(): void {
    navBarHover();
  }
  prepareRoute(outlet: RouterOutlet) {
    return null;
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation']
  }
}
