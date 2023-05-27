import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { UsuarioService } from './usuario.service';
import { SerieService } from './serie.service';
import { InicioComponent } from './inicio/inicio.component';
import { AgregarSerieComponent } from './agregar-serie/agregar-serie.component';
import { FacturasComponent } from './facturas/facturas.component';

@NgModule({
  declarations: [
    AppComponent,
    InicioComponent,
    AgregarSerieComponent,
    FacturasComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [UsuarioService, SerieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
