//tslint:disable
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class DataService {
  private headers = new HttpHeaders({Authorization : 'Basic ' + btoa('immobi' + ':' + '0000')})
  constructor(private http: HttpClient) { }

  getBasicSearch(): Observable<any> {
    this.headers.append('Content-Type', 'text/plain; charset=utf-8');

    this.http.post(
      "http://127.0.0.1:4300/auth/X",
      {},
      {headers: this.headers, responseType:"text"}
      ).subscribe(
        (response)=>{console.log(response);}
      )


    let data = this.http.get(
      "http://127.0.0.1:4300/auth/wahid",
      { headers: this.headers, responseType: "text" }
    )
    return data;
  }
}
