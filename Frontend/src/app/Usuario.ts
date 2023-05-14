import { Serie } from "./serie";
import { Factura } from "./factura";

export interface Usuario {
    username: string;
    password: string;
    iban: string;
    pendientes: Array<Serie>;
    empezadas: Array<Serie>;
    terminadas: Array<Serie>;
    facturas: Array<Factura>;
    cuota: number;
}