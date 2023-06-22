import { Component, OnDestroy, OnInit } from '@angular/core';
import { SerieService } from '../serie.service';
import { UsuarioService } from '../usuario.service';
import { Serie } from '../serie';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-agregar-serie',
  templateUrl: './agregar-serie.component.html',
  styleUrls: ['./agregar-serie.component.css']
})

export class AgregarSerieComponent implements OnInit, OnDestroy {

  series: Serie[] = [];
  usuario: Partial<Usuario> = {};
  titulo: string = '';

  constructor(public serieService: SerieService, public usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.usuario = this.usuarioService.usuario;
    this.serieService.getSeries().subscribe({
      next: (data) => { this.series = data},
      error: (e) => {
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
    });
  }

  ngOnDestroy(): void{
    this.usuarioService.usuario = this.usuario;
  }

  buscador(){
    var search = (<HTMLInputElement>document.getElementById("barraBusqueda")).value;
    this.titulo = search;
  }

  cambiaVisibilidad(id: string) {
    var x: any= document.getElementById(id);
    if (x.style.display === "none") {
      x.style.display = "block";
    } else {
      x.style.display = "none";
    }
  }

  agregaSerie(id: number){
    if(this.usuario.username !== undefined){
      this.usuarioService.agregarSeriePendiente(this.usuario.username, id).subscribe({
        next: (data) => {this.usuario = data},
        error: (e) => {
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
      });
    }
  }

  cambiaTitulo(titulo: string){
    this.titulo = titulo;
  }
}
