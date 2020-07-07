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
  getRegions(): Observable<any>{
    return this.http.get("http://127.0.0.1:4300/lieux/regions/findAll")
  }
  getLieux(): Observable<any>{
    return this.http.get("http://127.0.0.1:4300/lieux/findAll")
  }
  saveLieux(data): Observable<any>{
    return this.http.post("http://127.0.0.1:4300/lieux/save", data, {headers:{'Content-Type':'application/json'}, responseType: "text"})
  }
  saveAdresses(data): Observable<any>{
    return this.http.post("http://127.0.0.1:4300/adresses/save", data, {headers:{'Content-Type':'application/json'}, responseType: "text"})
  }
  getRegion(): Observable<any>{
    return this.http.get("http://localhost:4300/lieux/region/findAll")
  }
  getLocals(): Observable<any>{
    return this.http.get("http://127.0.0.1:4300/local/findAll")
  }
  saveRegion(data): Observable<any>{
    return this.http.post("http://127.0.0.1:4300/lieux/region/save", data, {headers:{'Content-Type':'application/json'}, responseType: "text"})
  }
  getAdresses(): Observable<any>{
    return this.http.get("http://127.0.0.1:4300/adresses/findAll")
  }
  saveLocal(data): Observable<any>{
    return this.http.post("http://127.0.0.1:4300/local/save", data, {headers:{'Content-Type':'application/json'}, responseType: "text"});
  }
  isThere(type, value): Observable<any>{
    return this.http.get("http://127.0.0.1:4300/auth/isThere", {params:{"type": type, "value": value}});
  }
  supprimerVille(data): Observable<any>{
    return this.http.delete("http://127.0.0.1:4300/lieux/delete", {params:{"id": data}, responseType:"text"});
  }
  supprimerLieu(data): Observable<any>{
    return this.http.delete("http://127.0.0.1:4300/lieux/delete", {params:{"id": data}, responseType:"text"});
  }
  supprimerLocal(data): Observable<any>{
    return this.http.delete("http://127.0.0.1:4300/local/delete", {params:{"id": data}, responseType:"text"});
  }
  supprimerAdresse(data): Observable<any>{
    return this.http.delete("http://127.0.0.1:4300/adresses/delete", {params:{"id": data}, responseType:"text"});
  }
  supprimerRegion(data): Observable<any>{
    return this.http.delete("http://127.0.0.1:4300/lieux/region/delete", {params:{"id": data}, responseType:"text"});
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
  adresses(): Observable<any>{
    return this.http.get("http://127.0.0.1:4300/adresses/findAll");
  }
  getTypes(): Observable<any>{
    return this.http.get("http://127.0.0.1:4300/types/findAll");
  }
  saveImages(data): Observable<any>{
    return this.http.post("http://127.0.0.1:4300/local/images/saveAll", data, {headers:{'Content-Type':'application/json'}});
  }

  updateLocal(data): Observable<any>{
    data['adresse'] = {id: data['adresse']}

    data['images'].forEach(item=>{
      delete item['base64']
    })
    return this.http.put("http://127.0.0.1:4300/local/update", data, {headers:{'Content-Type':'application/json'}, responseType: "text"})
  }
  updateAdresses(data): Observable<any>{
    return this.http.put("http://127.0.0.1:4300/adresses/update", data, {headers:{'Content-Type':'application/json'}, responseType: "text"})
  }
  updateRegion(data): Observable<any>{
    return this.http.put("http://127.0.0.1:4300/lieux/region/update", data, {headers:{'Content-Type':'application/json'}, responseType: "text"})
  }
  updateLieu(data): Observable<any>{
    return this.http.put("http://127.0.0.1:4300/lieux/update", data, {headers:{'Content-Type':'application/json'}, responseType: "text"})
  }
}
