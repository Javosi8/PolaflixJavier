package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Catalogo {
    
    private List<Serie> Series;

    public Catalogo(){
        Series = new ArrayList<Serie>();
    }

    public void añadirSerie(Serie serie){
        Series.add(serie);
    }

}
