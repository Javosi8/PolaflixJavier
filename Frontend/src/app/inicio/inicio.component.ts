import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  
  usuario: Partial<Usuario> = {};

  constructor(public usuarioService: UsuarioService){ }

  ngOnInit(): void {
    this.usuarioService.getUsuario(window.location.href.split("/")[4]).subscribe(
      data => {this.usuario = data}
    )
  }
}
