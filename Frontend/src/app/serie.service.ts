import { Injectable } from '@angular/core';
import { Serie } from './serie';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SerieService {

  private serieUrl = 'http://localhost:8080/series';

  constructor(private http: HttpClient) { }

  getSeries(): Observable<Serie[]>{
    return this.http.get<Serie[]>(this.serieUrl);
  }

  getSerie(idSerie: number): Observable<Serie>{
    const url = `${this.serieUrl}/${idSerie}`;
    return this.http.get<Serie>(url);
  }

  getSerieByInicial(inicial: string): Observable<Serie[]>{
    const url = `${this.serieUrl}?inicial=${inicial}`;
    return this.http.get<Serie[]>(url);
  }

  getSerieByTitulo(titulo: string): Observable<Serie[]>{
    const url = `${this.serieUrl}?titulo=${titulo}`;
    return this.http.get<Serie[]>(url);
  }
}
