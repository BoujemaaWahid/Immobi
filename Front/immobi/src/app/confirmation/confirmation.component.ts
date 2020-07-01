//tslint:disable
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DataService } from '../data-service.service';
declare var $: any;
@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit {

  redirect = -1;
  done = false;
  constructor(private data: DataService, private router: ActivatedRoute, private route: Router) { }

  ngOnInit(): void {
    $("#baseMenu").css({'display':'none'})
    this.router.queryParams.subscribe(params => {
      let data = params['signature'];
      if( data == null ) { this.redirect = 0; return }
      try{
        let t = JSON.parse(atob(data))
        if( t['email'] == undefined || t['date'] == undefined || localStorage.getItem("register") == null ){
          this.redirect = 2;
          return;
        }else if( this.validSignature(t['date']) ){
          this.redirect = 1;
        }else{
          this.redirect = 2;
        }
      }catch(e){ this.redirect = 2}

    });
  }


  validSignature(sign: string): boolean{
    let def = sign.split(" ")
    let fullYear = def[0].split("-")
    let time = def[1].split(":")
    let date = new Date()
    let now = new Date()
    date.setUTCFullYear(parseInt(fullYear[0]), parseInt(fullYear[1]), parseInt(fullYear[2]))
    date.setMinutes(parseInt(time[1]))
    date.setHours(parseInt(time[0]))
    date.setMinutes( date.getMinutes() + 60 )
    now.setMonth( now.getMonth() + 1)

    if( date.getTime() >  now.getTime() ){
      this.data.registerUser(localStorage.getItem("register")).subscribe(res=>{
        console.log(res, "register done.")
        localStorage.removeItem("register")
        localStorage.setItem("idUser", ""+res)
        $(".loading").removeClass("loading")
      })
      return true;
    }
    return false;
  }
  redirection(){
    if( this.redirect == 0 || this.redirect == 2) this.route.navigate([''])
    else if ( this.done == true ){
      //redirect to account
    }
  }

}
