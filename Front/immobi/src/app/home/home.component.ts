//tslint:disable
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { AOS } from 'aos';
declare var $: any;
declare var AOS: any;
declare var HomeAnime: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {
  categoryContent = []
  basicSearchInput = ''
  constructor() {

    this.categoryContent = [
    { category: 'Paris', description:"Maison qui contient des trucs",
    image:'assets/logo.png', price:'4475.123$', title: 'Maison 1' },
    { category: 'Paris', title: '' },
    { category: 'North America', title: 'Canada' },
    { category: 'Asia', title: 'South Korea' },
    { category: 'Asia', title: 'Japan' },
    { category: 'Asia', title: 'China' },
    { category: 'Europe', title: 'Denmark' },
    { category: 'Europe', title: 'England' },
    { category: 'Europe', title: 'France' },
    { category: 'Europe', title: 'Germany' },
    { category: 'Africa', title: 'Ethiopia' },
    { category: 'Africa', title: 'Nigeria' },
    { category: 'Africa', title: 'Zimbabwe' },
  ];
  }

  ngAfterViewInit(): void {
  }
  HideConnectMsgInfo(){
    HomeAnime().hideConnectMsgInfo()
  }
  basicSearchClick(){
    HomeAnime().showConnectMsgInfo()
  }
  ngOnInit(): void {
    HomeAnime().init()

    $('.ui.search')
    .search({
      type: 'category',
      searchFields   : [
        'title',
        'category',
        'price'
      ],
      source: this.categoryContent
    });
  }
}
