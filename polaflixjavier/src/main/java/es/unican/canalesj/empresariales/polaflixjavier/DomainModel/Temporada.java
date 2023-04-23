package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Temporada implements Comparable<Temporada>{
    
    @Id
    @GeneratedValue
    private long id;

    private int numTemporada;

    @ManyToOne
    private Serie serie;

    @OneToMany(mappedBy = "temporada")
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
        return Objects.hash(numTemporada, serie, capitulos);
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
        return ((this.serie.equals(temporada.getSerie())) && (this.numTemporada == temporada.getNumTemporada())
                && (this.capitulos.equals(temporada.getCapitulos())));
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
