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

  constructor(public serieService: SerieService, public usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.usuarioService.getUsuario().subscribe(
      data => {this.usuario = data}
    )
    this.serieService.getSeries().subscribe(
      data => { this.series = data}
    );
  }

  buscador(){
    var lista : Array<Serie> = [];
    var search = (<HTMLInputElement>document.getElementById("barraBusqueda")).value;
    this.series.forEach( function(valor) {
      if(valor.titulo.startsWith(search)){
        lista.push(valor);
      }
    });
    if(lista.length == 0){
      alert("No hay coincidencias")
    }
    this.crearDivSeries(lista);
  }

  mostrarSeries(letra:string){
    var lista : Array<Serie> = [];
    if(letra.length > 0){
      this.series.forEach( function(valor) {
        if(valor.titulo[0] == letra){
            lista.push(valor);
        }
      });
    }
    this.crearDivSeries(lista);
  }

  crearDivSeries(lista :Serie[]){
    var self = this;
    var div: any = document.getElementById('series');
    div.innerHTML = '';
    lista.sort();
    lista.forEach( function(valor) {
      var elemento1 = self.crearElemento("label", valor.titulo, ["class", "etiquetaSerie"]);
      elemento1.addEventListener('click', () => {
        self.cambiaVisibilidad(valor.titulo);
      });
      var elemento2 = self.crearElemento("input", null, ["type", "button", "id", valor.titulo+"agregar", "value", "Agregar Serie", "class", "inputs"]);
      elemento2.addEventListener('click', () => {
        if(self.usuario.username !== undefined){
          self.usuarioService.agregarSeriePendiente(self.usuario.username, valor.id).subscribe(data => {
            self.usuario = data;
        });
        }
      });
      var elemento3 = self.crearElemento("br", null, []);
      var elemento4 = self.crearElemento("div", null, ["id", valor.titulo, "style", "display: none;","class", "tablita"]);
      var elemento5 = self.crearElemento("p", valor.sinopsis, ["class", "sinopsis"]);
      var elemento51 = self.crearElemento("p", "Creadores: "+valor.creadores.map(function(item) {return item.nombre + " " + item.apellido;}), ["class", "creadores"]);
      var elemento52 = self.crearElemento("p", "Actores: "+valor.actores.map(function(item) {return item.nombre + " " + item.apellido;}), ["class", "actores"]);
      var elemento6 = self.crearElemento("input", null, ["type", "button", "id", valor.titulo+"cerrar", "value", "Cerrar", "class", "inputs"]);
      elemento6.addEventListener('click', () => {
        self.cambiaVisibilidad(valor.titulo);
      });
      var elemento7 = self.crearElemento("br", null, []);
      var elemento8 = self.crearElemento("br", null, []);
      elemento4.appendChild(elemento5);
      elemento4.appendChild(elemento51);
      elemento4.appendChild(elemento52);
      elemento4.appendChild(elemento6);
      elemento4.appendChild(elemento7);
      elemento4.appendChild(elemento8);
      div.appendChild(elemento1);
      div.appendChild(elemento2);
      div.appendChild(elemento3);
      div.appendChild(elemento4);
      div.appendChild(elemento7);
      div.appendChild(elemento8);
    });

  }

  crearElemento(etiqueta: string, texto: any, argumentos:any[]) {
    var elemento = document.createElement(etiqueta);
    if (texto !== null) {
        elemento.appendChild(document.createTextNode(texto));
    }
    if (argumentos.length >= 2) {
        for (var i = 0; i < argumentos.length; i = i + 2) {
            elemento.setAttribute(argumentos[i], argumentos[i + 1]);
        }
    }
    return elemento;
  }

  cambiaVisibilidad(id: string) {
  var x: any= document.getElementById(id);
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

}
