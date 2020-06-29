//tslint:disable
import { Injectable } from '@angular/core';
import { HttpInterceptor } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor() { }

  intercept(req, next){

    const signature = req.clone({
      setHeaders: {
        Authorization : 'Basic ' + btoa('immobi' + ':' + '0000'),
      }
    });

    return next.handle(signature);
  }
}
