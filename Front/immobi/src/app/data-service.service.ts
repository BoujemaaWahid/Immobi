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
      "http://127.0.0.1:4300/t",
      {},
      {headers: this.headers, params:{'data':'45'}, responseType:"text"}
      ).subscribe(m => {console.log(m)})



    let data = this.http.get(
      "http://127.0.0.1:4300/basicFilter",
      { headers: this.headers, params:{data:"xxsx"}, responseType: "text" }
    )
    return data;
  }
}
