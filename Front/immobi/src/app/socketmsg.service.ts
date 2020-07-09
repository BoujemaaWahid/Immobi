//tslint:disable
import { Injectable } from '@angular/core';
import io from 'socket.io-client';
@Injectable({
  providedIn: 'root'
})
export class SocketmsgService {
  private socket: any;
  constructor() { 
    this.connect()
  }
  connect(){
    this.socket = io("http://127.0.0.1:4300/ws",{
    extraHeaders: {
      Authorization: 'Basic ' + btoa("immobi"+':'+"0000")
    }
  })
  }
}
