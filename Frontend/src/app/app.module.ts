import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { UsuarioService } from './usuario.service';
import { SerieService } from './serie.service';
import { InicioComponent } from './inicio/inicio.component';
import { AgregarSerieComponent } from './agregar-serie/agregar-serie.component';

@NgModule({
  declarations: [
    AppComponent,
    InicioComponent,
    AgregarSerieComponent,
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
