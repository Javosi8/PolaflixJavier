import { Entrada } from "./entrada";

export interface Factura {
    id:number;
    costeTotal: number;
    fecha: Date;
    mes: number;
    año: number;
    entradasFactura: Array<Entrada>;
}