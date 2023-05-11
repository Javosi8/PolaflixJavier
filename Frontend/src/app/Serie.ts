import { Creador } from "./Creador";
import { Actor } from "./Actor";
import { Temporada } from "./Temporada";

export interface Serie{
    titulo: string;
    inicial: string;
    sinopsis: string;
    temporadas: Array<Temporada>;
    actores: Array<Actor>;
    creadores: Array<Creador>;
}