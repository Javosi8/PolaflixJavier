import { Creador } from "./creador";
import { Actor } from "./actor";
import { Temporada } from "./temporada";

export interface Serie{
    id: number;
    titulo: string;
    inicial: string;
    sinopsis: string;
    temporadas: Array<Temporada>;
    actores: Array<Actor>;
    creadores: Array<Creador>;
}