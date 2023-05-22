import { Component, OnInit } from '@angular/core';
import { SerieService } from '../serie.service';
import { UsuarioService } from '../usuario.service';
import { Serie } from '../serie';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-agregar-serie',
  templateUrl: './agregar-serie.component.html',
  styleUrls: ['./agregar-serie.component.css']
})

export class AgregarSerieComponent implements OnInit {

  series: Serie[] = [];
  usuario: Partial<Usuario> = {};
  titulo: string = '';

  constructor(public serieService: SerieService, public usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.usuario = this.usuarioService.usuario;
    this.serieService.getSeries().subscribe(
      data => { this.series = data}
    );
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
      this.usuarioService.agregarSeriePendiente(this.usuario.username, id).subscribe(data => {
        this.usuario = data;
      });
    }
  }

  cambiaTitulo(titulo: string){
    this.titulo = titulo;
  }
}
