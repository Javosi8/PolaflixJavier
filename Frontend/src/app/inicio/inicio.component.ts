import { Component, OnDestroy, OnInit } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit, OnDestroy {
  
  usuario: Partial<Usuario> = {};

  constructor(public usuarioService: UsuarioService, private route: ActivatedRoute){ }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.cargaUsuario(params['username'])
    })
  }

  cargaUsuario(username : string | null) {
    console.log(username);
    if (!(username === null)) {
      this.usuarioService.getUsuario(username).subscribe({
        next: (data) => {this.usuario = data},
        error: (e) =>{alert("Error: " + e.status + "\n" + e.message)}
      })
    }
  }

  ngOnDestroy(): void {
      this.usuarioService.usuario = this.usuario;
  }
  
}
