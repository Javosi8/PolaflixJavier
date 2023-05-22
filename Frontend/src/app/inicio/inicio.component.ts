import { Component, OnDestroy, OnInit } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit, OnDestroy {
  
  usuario: Partial<Usuario> = {};

  constructor(public usuarioService: UsuarioService){ }

  ngOnInit(): void {
    this.usuarioService.getUsuario().subscribe(
      data => {this.usuario = data}
    )
  }

  ngOnDestroy(): void {
      this.usuarioService.usuario = this.usuario;
  }
  
}
