import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';
import { Factura } from './factura';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private usuarioUrl = 'http://localhost:8080/usuarios';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getUsuario(nombre: string): Observable<Usuario>{
    const url = `${this.usuarioUrl}/${nombre}`;
    return this.http.get<Usuario>(url);
  }

  agregarSeriePendiente(nombre: string, idSerie: number): Observable<Usuario>{
    const url = `${this.usuarioUrl}/${nombre}/agregarSeriePendientes/${idSerie}`;
    return this.http.put<Usuario>(url, null, this.httpOptions);
  }

  verCapitulo(nombre: string, idSerie: number, idTemporada: number, idCapitulo: number): Observable<Usuario>{
    const url = `${this.usuarioUrl}/${nombre}/verCapitulo?idSerie=${idSerie}&idTemporada=${idTemporada}&idCapitulo=${idCapitulo}`;
    return this.http.put<Usuario>(url, null, this.httpOptions);
  }

  getFacturas(nombre: string): Observable<Factura[]>{
    const url = `${this.usuarioUrl}/${nombre}/facturas`;
    return this.http.get<Factura[]>(url);
  }
}
