package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;

@Entity
public class Temporada implements Comparable<Temporada>{
    
    private int NumTemporada;

    private Serie Serie;

    private List<Capitulo> Capitulos;

    public Temporada(int NumTemporada, Serie Serie){
        this.NumTemporada = NumTemporada;
        this.Serie = Serie;

        Capitulos = new ArrayList<Capitulo>();
    }

    //#region Getters
    public int getNumTemporada() {
        return NumTemporada;
    }
    public Serie getSerie() {
        return Serie;
    }
    //#endregion

    //#region Setters
    public void setNumTemporada(int numTemporada) {
        NumTemporada = numTemporada;
    }

    public void setSerie(Serie serie) {
        Serie = serie;
    }
    //#endregion
    
    @Override
    public int hashCode(){
        return Objects.hash(NumTemporada, Serie);
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }

        if(!(o instanceof Temporada)){
            return false;
        }

        Temporada temporada = (Temporada)o;
        return ((this.Serie.equals(temporada.getSerie())) && (this.NumTemporada == temporada.getNumTemporada()));
    }

    @Override
    public int compareTo(Temporada temporada){
        if(this.getNumTemporada() > temporada.getNumTemporada()){
            return 1;
        }
        if(this.getNumTemporada() < temporada.getNumTemporada()){
            return -1;
        }
        return 0;
    }
}
