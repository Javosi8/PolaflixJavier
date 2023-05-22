import { Component, OnInit } from '@angular/core';
import { UsuarioService } from './usuario.service';
import { Usuario } from './usuario';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{
  title = 'Polaflix';

  usuario: Partial<Usuario> = {};

  constructor(public usuarioService: UsuarioService){}

  ngOnInit(): void {
    this.usuarioService.getUsuario().subscribe(
      data => {this.usuario = data}
    )
  }
}
