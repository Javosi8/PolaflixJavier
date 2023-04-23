package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Entrada {

    private double coste;
    private Date fecha;
    private String nombreSerie;
    private int numeroTemporada;
    private int numeroCapitulo;

    protected Entrada(){
        
    }

    public Entrada(double coste, Date fecha, String nombreSerie, int numeroTemporada, int numeroCapitulo){
        this.coste = coste;
        this.fecha = fecha;
        this.nombreSerie = nombreSerie;
        this.numeroTemporada = numeroTemporada;
        this.numeroCapitulo = numeroCapitulo;
    }

    //#region Getters
    public double getCoste() {
        return coste;
    }
    public Date getFecha() {
        return fecha;
    }
    public String getNombreSerie() {
        return nombreSerie;
    }
    public int getNumeroTemporada() {
        return numeroTemporada;
    }
    public int getNumeroCapitulo() {
        return numeroCapitulo;
    }
    //#endregion

    //#region Setters
    public void setCoste(double coste) {
        this.coste = coste;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setNombreSerie(String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }
    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }
    public void setNumeroCapitulo(int numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }
    //#endregion

    @Override
    public int hashCode(){
        return Objects.hash(coste, fecha, nombreSerie, numeroTemporada, numeroCapitulo);
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }

        if(!(o instanceof Entrada)){
            return false;
        }

        Entrada entrada = (Entrada)o;
        return ((this.coste == entrada.getCoste()) && (this.fecha.equals(entrada.getFecha())) && (this.nombreSerie.equals(entrada.getNombreSerie()))
                && (this.numeroTemporada == entrada.getNumeroTemporada()) && (this.numeroCapitulo == entrada.getNumeroCapitulo()));
    }
}
