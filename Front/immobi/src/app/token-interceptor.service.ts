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

    const signature = req.clone({
      setHeaders: {
        Authorization : 'Basic ' + btoa(localStorage.getItem("authorize")),
      }
    });

    return next.handle(signature);
  }
}
