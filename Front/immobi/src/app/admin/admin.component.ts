//tslint:disable
import { Component, OnInit } from '@angular/core';
import { Headers } from './Headers';
import { Router, ActivatedRoute } from '@angular/router';
import { SocketmsgService } from '../socketmsg.service';
import { DataService } from '../data-service.service';
declare var $: any;
declare var Tabulator: any;
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  countMessages = 0;
  constructor(private services: DataService, private sservice: SocketmsgService, private route: Router, private aroute: ActivatedRoute) {
    
    if( localStorage.getItem("idUser") == null ){
      this.route.navigate(['/login'])
    }else if( localStorage.getItem("user_type") != "1" ){this.route.navigate(['/login'])}

   }

  

  ngOnInit(): void {
    this.services.getMessagesCount().subscribe(res=>this.countMessages = res)
    this.sservice.streamData.subscribe(res=>{
      if( res == null )return;
      
      $("#messagesicon").transition('jiggle')
      this.countMessages+=1
    })
    this.sservice.streamRead.subscribe(res=>{
      if ( res == null )return;
      this.countMessages -= 1;
    })
    
    $("#baseMenu").css({display:'none'})
    this.locales()
  }
  send(){
    alert()
    this.sservice.send("hello 2")
  }
  toggleMenu(){
    $('.ui.labeled.icon.sidebar').sidebar('toggle')
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
  regions(){
    this.route.navigate(['regions'], {relativeTo: this.aroute})
  }
  acceuil(){
    this.route.navigate([''])
  }
  messages(){
    console.log("oooooooo")
    this.route.navigate(['messages'], {relativeTo: this.aroute})
  }
  deconnect(){
    localStorage.removeItem("idUser")
    localStorage.removeItem("user_type")
    localStorage.removeItem("authorize")
    this.route.navigate(['login'])
  }
}
