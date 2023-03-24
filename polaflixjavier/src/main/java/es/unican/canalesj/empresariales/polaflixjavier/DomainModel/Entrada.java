package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Embeddable
public class Entrada {
    
    @Id
    @GeneratedValue
    private long id;

    private double Coste;
    private Date Fecha;
    private String NombreSerie;
    private int NumeroTemporada;
    private int NumeroCapitulo;

    public Entrada(double coste, Date fecha, String nombreSerie, int numeroTemporada, int numeroCapitulo){
        this.Coste = coste;
        this.Fecha = fecha;
        this.NombreSerie = nombreSerie;
        this.NumeroTemporada = numeroTemporada;
        this.NumeroCapitulo = numeroCapitulo;
    }

    //#region Getters
    public double getCoste() {
        return Coste;
    }
    public Date getFecha() {
        return Fecha;
    }
    public String getNombreSerie() {
        return NombreSerie;
    }
    public int getNumeroTemporada() {
        return NumeroTemporada;
    }
    public int getNumeroCapitulo() {
        return NumeroCapitulo;
    }
    //#endregion

    //#region Setters
    public void setCoste(double coste) {
        Coste = coste;
    }
    public void setFecha(Date fecha) {
        Fecha = fecha;
    }
    public void setNombreSerie(String nombreSerie) {
        NombreSerie = nombreSerie;
    }
    public void setNumeroTemporada(int numeroTemporada) {
        NumeroTemporada = numeroTemporada;
    }
    public void setNumeroCapitulo(int numeroCapitulo) {
        NumeroCapitulo = numeroCapitulo;
    }
    //#endregion

    @Override
    public int hashCode(){
        return Objects.hash(Coste, Fecha, NombreSerie, NumeroTemporada, NumeroCapitulo);
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
        return ((this.Coste == entrada.getCoste()) && (this.Fecha.equals(entrada.getFecha())) && (this.NombreSerie.equals(entrada.getNombreSerie()))
                && (this.NumeroTemporada == entrada.getNumeroTemporada()) && (this.NumeroCapitulo == entrada.getNumeroCapitulo()));
    }
}
