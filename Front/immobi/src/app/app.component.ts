//tslint:disable
import { Component, AfterViewInit, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { slider } from '../route-animations';
import { SocketmsgService } from './socketmsg.service';

declare var $: any;
declare var navBarHover: any;
declare var navBarActions: any;
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: []
})
export class AppComponent implements OnInit, AfterViewInit{
  constructor(private ws: SocketmsgService){
    
  }
  ngAfterViewInit(): void {

  }
  ngOnInit(): void {
    navBarHover();
    navBarActions().InitScrollInside();

  }

  prepareRoute(outlet: RouterOutlet) {
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData['animation']
  }
}
