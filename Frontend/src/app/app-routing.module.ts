import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
import { AgregarSerieComponent } from './agregar-serie/agregar-serie.component';
import { FacturasComponent } from './facturas/facturas.component';

const routes: Routes = [
  {path: '', redirectTo: '/usuarios/Javier', pathMatch: 'full'},
  {path: 'usuarios/:username', component: InicioComponent},
  {path: 'agregarSerie', component: AgregarSerieComponent},
  {path: 'facturas', component: FacturasComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
