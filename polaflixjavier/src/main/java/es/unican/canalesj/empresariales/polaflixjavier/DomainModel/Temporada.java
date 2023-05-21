package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.canalesj.empresariales.polaflixjavier.Views;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Temporada implements Comparable<Temporada>{
    
    @Id
    @GeneratedValue
    @JsonView({Views.DescripcionSerie.class})
    private long id;

    @JsonView({Views.DescripcionSerie.class})
    private int numTemporada;

    @ManyToOne
    @JsonBackReference
    private Serie serie;

    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonView({Views.DescripcionSerie.class})
    private SortedSet<Capitulo> capitulos;

    protected Temporada(){
        
    }

    public Temporada(int numTemporada, Serie serie){
        this.numTemporada = numTemporada;
        this.serie = serie;

        capitulos = new TreeSet<Capitulo>();
    }

    public void agregarCapitulo(Capitulo capitulo){
        capitulos.add(capitulo);
    }

    public Capitulo getCapituloById(int numCapitulo){
        return capitulos.stream().filter(c -> c.getNumCapitulo() == numCapitulo).findFirst().get();
    }

    //#region Getters
    public int getNumTemporada() {
        return numTemporada;
    }
    public Serie getSerie() {
        return serie;
    }
    public SortedSet<Capitulo> getCapitulos() {
        return capitulos;
    }
    //#endregion

    //#region Setters
    public void setNumTemporada(int numTemporada) {
        this.numTemporada = numTemporada;
    }
    public void setSerie(Serie serie) {
        this.serie = serie;
    }
    public void setCapitulos(SortedSet<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }
    //#endregion
    
    @Override
    public int hashCode(){
        return Objects.hash(numTemporada, serie);
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
        return ((this.serie.equals(temporada.getSerie())) && (this.numTemporada == temporada.getNumTemporada()));
    }

    @Override
    public int compareTo(Temporada temporada){
        if(this.numTemporada > temporada.getNumTemporada()){
            return 1;
        }
        if(this.numTemporada < temporada.getNumTemporada()){
            return -1;
        }
        return 0;
    }
}
