//tslint:disable
import { Injectable } from '@angular/core';
import { HttpInterceptor } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor() {
    if ( localStorage.getItem("authorize") == null )
      localStorage.setItem("authorize", "immobi:0000")
  }

  intercept(req, next){
    
    if ( localStorage.getItem("authorize") == null )
      localStorage.setItem("authorize", "immobi:0000")
      
    let auth = localStorage.getItem("authorize").split(":")
    const signature = req.clone({
      setHeaders: {
        Authorization : 'Basic ' + btoa(auth[0]+':'+auth[1]),
      }
    });

    return next.handle(signature);
  }
}
