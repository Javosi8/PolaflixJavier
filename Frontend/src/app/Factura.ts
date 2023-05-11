import { Entrada } from "./Entrada";

export interface Factura {
    costeTotal: number;
    fecha: Date;
    mes: number;
    año: number;
    entradasFactura: Array<Entrada>;
}