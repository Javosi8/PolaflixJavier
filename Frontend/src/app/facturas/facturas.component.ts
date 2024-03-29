import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario';
import { Factura } from '../factura';

@Component({
  selector: 'app-facturas',
  templateUrl: './facturas.component.html',
  styleUrls: ['./facturas.component.css']
})
export class FacturasComponent implements OnInit, OnDestroy{

  usuario: Partial<Usuario> = {};
  facturas: Factura[] = [];
  facturaActual: number = 0;

  constructor(public usuarioService: UsuarioService, private cdr: ChangeDetectorRef){ }

  ngOnInit(): void {
    this.usuario = this.usuarioService.usuario;
    this.usuarioService.getFacturas(this.usuario.username!).subscribe({
      next: (data) => {
        this.facturas = data;
        this.facturaActual = this.facturas.length;
        this.cdr.detectChanges();
      },
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

  ngOnDestroy(): void {
      this.usuarioService.usuario = this.usuario;
  }

  facturaAnterior(){
    if(this.facturaActual < this.facturas.length){
      this.facturaActual += 1;
    }
  }

  facturaSiguiente(){
    if(this.facturaActual > 1){
      this.facturaActual -= 1;
    }
  }

}
