import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  
  usuario: Partial<Usuario> = {};

  constructor(public usuarioService: UsuarioService, public route: ActivatedRoute){ }

  ngOnInit(): void {
    // this.usuarioService.getUsuario(window.location.href.split("/")[4]).subscribe(
    //  data => {this.usuario = data}
    // )
 
    this.route.queryParamMap
      .subscribe(params => {
        var username = params.get('username')
        if(username !== null){
          this.usuarioService.getUsuario(username).subscribe(
            data => {this.usuario = data}
        )}
      }
    );
  }
}
