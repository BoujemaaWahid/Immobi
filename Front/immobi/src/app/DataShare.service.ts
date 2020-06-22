//tslint:disable
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DataShare {
  private messageSource = new BehaviorSubject<any>("ok");
  currentMessage = this.messageSource.asObservable();
  constructor() { }
  changeMessage(message: any) {
    console.log(message)
    this.messageSource.next(message)
  }
}
