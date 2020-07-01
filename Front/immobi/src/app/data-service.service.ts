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
  isThere(type, value): Observable<any>{
    return this.http.get("http://127.0.0.1:4300/auth/isThere", {params:{"type": type, "value": value}});
  }
  compteValidation(email): Observable<any>{
    return this.http.get("http://127.0.0.1:4300/auth/validation", {params:{"email": email}});
  }
  registerUser(user): Observable<any>{
    return this.http.post("http://127.0.0.1:4300/auth/register", user, {headers:{'Content-Type':'application/json'}});
  }
  connectAccount(data): Observable<any>{
    return this.http.post("http://127.0.0.1:4300/auth/login", data, {headers:{'Content-Type':'application/json'}});
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
