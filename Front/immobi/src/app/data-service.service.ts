//tslint:disable
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class DataService {
  constructor(private http: HttpClient) { }

  getLieuxEtRegions(): Observable<any> {
    return this.http.get("http://127.0.0.1:4300/lieux/lieuxRegion");
  }

  getLocalsWithFilters(filter: any): Observable<any>{
    return this.http.get("http://127.0.0.1:4300/local/filters",
    {headers:{}, params: {"data": JSON.stringify(filter)}}
    )
  }

  getLocals(): Observable<any>{
    return this.http.get("http://127.0.0.1:4300/local/findAll")
  }
/*
    this.http.post(
      "http://127.0.0.1:4300/auth/X",
      {},
      {headers: this.headers, responseType:"text"}
      ).subscribe(
        (response)=>{

          console.log(response);}
      )*/
}
