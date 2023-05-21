import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';
import { Factura } from './factura';

@Injectable({
  providedIn: 'root'
})

export class UsuarioService {

  private baseUrl = 'http://localhost:8080/usuarios/';
  private usuarioUrl: string;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {
    this.usuarioUrl = 'http://localhost:8080/usuarios/' + window.location.href.split("/")[4];
  }

  getUsuario(): Observable<Usuario>{
    const url = `${this.usuarioUrl}`;
    return this.http.get<Usuario>(url);
  }

  agregarSeriePendiente(nombre: string, idSerie: number): Observable<Usuario>{
    const url = `${this.baseUrl}/${nombre}/pendientes/${idSerie}`;
    return this.http.put<Usuario>(url, null, this.httpOptions);
  }

  verCapitulo(nombre: string, idSerie: number, idTemporada: number, idCapitulo: number): Observable<Usuario>{
    const url = `${this.baseUrl}/${nombre}/verCapitulo?idSerie=${idSerie}&idTemporada=${idTemporada}&idCapitulo=${idCapitulo}`;
    return this.http.put<Usuario>(url, null, this.httpOptions);
  }

  getFacturas(nombre: string): Observable<Factura[]>{
    const url = `${this.baseUrl}/${nombre}/facturas`;
    return this.http.get<Factura[]>(url);
  }
}
