//tslint:disable
import { Component, OnInit, AfterViewInit, OnDestroy, Input } from '@angular/core';
import { DataService } from '../data-service.service';
import { Reponse } from './Reponse';
import { SocketmsgService } from '../socketmsg.service';
declare var Tabulator: any;
declare var $: any;
declare var Swal: any;
@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit, OnDestroy {
  data: any;
  messages = [];
  news = 0;
  item = new Reponse();
  nresp = 0;
  constructor(private sservices: SocketmsgService, private services: DataService) { 
    this.services.getMessages().subscribe(res=> {
      console.log(res)
      this.messages = res
      this.news = res.filter(e=> e.vue == false).length;
      this.nresp = res.length;
    })
    this.sservices.streamData.subscribe(res=> {
      if( res == null )return;
      this.data = JSON.parse(res);
      console.log(this.data)
      this.messages.push( JSON.parse(res) )
      this.news += 1
      this.nresp += 1
      $("#sreps").transition("jiggle")
      $("#nouveaux").transition('jiggle')
    })  
  }
  ngOnDestroy(): void {
    $(".modal").remove()
  }
  
  ngOnInit(): void {
    $(".ui.modal").modal({
      onHide:()=>{
        this.item = new Reponse()
      }
    })
  }
  reponse(id){
    let msg = this.messages[id]
    this.item.id = id;
    this.item.itemId = msg.id
    this.item.titre = (msg.local == null)?'Message':msg.local.titre
    this.item.email = msg.email;
    this.item.tel = msg.tel;
    this.item.base64 = (msg.local == null)?'assets/mail.png':msg.local.images[0].base64;
    this.item.message = msg.message;
    this.item.type = (msg.local == null)?'message':'demande';
    if( !msg.vue ){
      msg.vue = true
      this.news -= 1;
      this.services.setSeen({id: msg.id, type: this.item.type}).subscribe()
      this.sservices.streamRead.next(1)
    }
    $(".ui.modal").modal('show')
  }
  sendMessage(){
    this.services.sendMessage({email: this.item.email, message: this.item.reponse, id:this.item.itemId, type:this.item.type}).subscribe()
    this.nresp -= 1
    $("#"+this.item.id).fadeOut(500)
    $(".ui.modal").modal('hide')
  }

}
