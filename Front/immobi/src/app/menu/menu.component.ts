//tslint:disable
import { Component, OnInit, HostBinding, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { SocketmsgService } from '../socketmsg.service';
import { DataService } from '../data-service.service';
import { DataShare } from '../dataShare.service';
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
    disp: !localStorage.getItem("idUser"),
    type: localStorage.getItem("user_type"),
    id: null,
    favs: JSON.parse( localStorage.getItem("favoires") ),
    locals: []
  }
  constructor(private router: Router,private dataShare: DataShare, private services: DataService, private conSub: SocketmsgService,private route: Router) {
    services.getLocals().subscribe(res=> {
      this.display.locals = res
    })
    conSub.conSub.subscribe(res=>{
      if ( res == null )return;
        if( res['auth'] ){
          this.display.type = "2"
          this.display.id = res.id
          this.display.disp = false
          services.getFavs(res.id).subscribe(res=> {
            localStorage.setItem("favoires", JSON.stringify(res['favoires']))
            this.display.favs = res['favoires']
            
          })
        }
        else if ( res['update'] ){
          this.display.favs = JSON.parse( localStorage.getItem("favoires") )
        }
    })
  }
  rmFav(id){
    this.services.supprimerFav({idu: localStorage.getItem("idUser"), idl: id}).subscribe(res=>{
      this.display.favs = this.display.favs.filter(e=> e.id != id )
      localStorage.setItem("favoires", JSON.stringify(this.display.favs))
    })
  }
  deconnect(){
    localStorage.removeItem("idUser")
    localStorage.removeItem("user_type")
    localStorage.removeItem("authorize")
    this.display.type = null
    this.display.disp = true
    this.display.id = null
    this.route.navigate(['login'])
  }
  seeDetails(event, id){
    
    $('#listFav').sidebar('toggle')
    event.preventDefault()
    let e = this.display.locals.filter(e=> e.id == id )[0]
    this.dataShare.changeMessage(e)
    this.router.navigate(['details']);
  }
  account(){
    let tp = localStorage.getItem("user_type")
    if( tp == '1') this.route.navigate(['admin'])
  }
  ngOnInit(): void {
    navBarActions().updateActiveLink()
    navBarActions().hmUpdateActiveLink()
  }
  Favoires(){
    $('#listFav')
  .sidebar('toggle')
  }
  showSideMenu(){
    navBarActions().navAnimation()
  }

}
