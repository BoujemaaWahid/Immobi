//tslint:disable
import { Injectable } from '@angular/core';
import { HttpInterceptor } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor() {
    if ( localStorage.getItem("authorize") == null )
      localStorage.setItem("authorize", "admin:0000")
  }

  intercept(req, next){

    const signature = req.clone({
      setHeaders: {
        Authorization : 'Basic ' + btoa('admin'+':'+'0000'),
      }
    });

    return next.handle(signature);
  }
}
