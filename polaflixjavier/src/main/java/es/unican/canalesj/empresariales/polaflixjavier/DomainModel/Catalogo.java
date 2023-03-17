package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;

@Entity
public class Catalogo {
    
    private Set<Serie> Series;

    public Catalogo(){
        Series = new HashSet<Serie>();
    }

    public void añadirSerie(Serie serie){
        Series.add(serie);
    }

    public void eliminarSerie(Serie serie){
        Series.remove(serie);
    }

    public Serie getSerieByTitulo(String titulo){
        return Series.stream().filter(s -> s.getTitulo().equals(titulo)).findFirst().get();
    }

    //#region Getters
    public Set<Serie> getSeries() {
        return Series;
    }
    //#endregion

    //#region Setters
    public void setSeries(Set<Serie> series) {
        Series = series;
    }
    //#endregion

}
