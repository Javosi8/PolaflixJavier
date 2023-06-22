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
    if (!(username === null)) {
      this.usuarioService.getUsuario(username).subscribe({
        next: (data) => {this.usuario = data},
        error: (e) =>{
          switch(e.status){
            case 404:
              alert("Error: " + e.status + "\n" + "El recurso solicitado no existe en el sistema")
              break;
            case 500:
              alert("Error: " + e.status + "\n" + "Actualmente estamos experimentando problemas en nuestros sistemas\n"
                + "Por favor, vuelva a intentarlo en unos minutos. Disculpe las molestias")
              break;
          }
        }
      })
    }
  }

  ngOnDestroy(): void {
      this.usuarioService.usuario = this.usuario;
  }
  
}
