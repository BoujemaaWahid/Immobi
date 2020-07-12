//tslint:disable
import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Observable, from, Subject, ReplaySubject, BehaviorSubject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class SocketmsgService {
  private conn: any;
  private socket: any;
  private reslist = [];
  public stream: Observable<any>;
  public responses: Observable<any>;
  public streamData: Subject<any>;
  public streamRead: Subject<any>;
  public update: any;
  private l = []
  constructor() { 
    
    this.streamData = new BehaviorSubject(null)
    this.streamRead = new BehaviorSubject(null)
    this.connect()
  
  }
  send(message){
    this.stream.subscribe(res=>{
      res.send("/app/message", {}, message);
    })
  }
  public resps(){
    this.responses.subscribe(res=>{
      console.log(res)
    })
  }
  connect(){
    this.socket = new SockJS("http://127.0.0.1:4300/ws")
    this.conn = Stomp.over(this.socket);
    this.conn.debug = null
    this.responses = from(this.reslist)
    this.stream = new Observable(observer=>{
      this.conn.connect({}, ()=>{ 
        this.conn.subscribe("/rt/demande", message=> { this.streamData.next(message.body); } )
        this.conn.subscribe("/rt/2", message=> { this.reslist.push(message.body); } )
        observer.next(this.conn)
      })
    })
    this.stream.subscribe()
  }
    /*
    this.socket = new SockJS("http://127.0.0.1:4300/ws")
  
    //this.socket = new WebSocket("ws://127.0.0.1:4300/ws");
    this.conn = Stomp.over(this.socket);
    this.conn.debug = null
    let _this = this;
    this.conn.connect({}, frame =>{
      this.conn.send("/app/message", {}, "hello world");

      this.conn.subscribe("/rt/demande", function(message) {
        console.log("reply: ", message.body)
        
      })

      this.conn.subscribe("/rt/2", function(message) {
        console.log("reply: RT 2 ", message.body)
        
      })

    })

  }*/
  
}
