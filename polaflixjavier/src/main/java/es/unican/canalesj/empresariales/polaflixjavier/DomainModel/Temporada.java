package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Objects;
import java.util.Set;
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
    private long Id;

    private int NumTemporada;

    @ManyToOne
    private Serie Serie;

    @OneToMany(mappedBy = "Temporada")
    private Set<Capitulo> Capitulos;

    public Temporada(int NumTemporada, Serie Serie){
        this.NumTemporada = NumTemporada;
        this.Serie = Serie;

        Capitulos = new TreeSet<Capitulo>();
    }

    public void agregarCapitulo(Capitulo capitulo){
        Capitulos.add(capitulo);
    }

    public Capitulo getCapituloById(int numCapitulo){
        return Capitulos.stream().filter(c -> c.getNumCapitulo() == numCapitulo).findFirst().get();
    }

    //#region Getters
    public int getNumTemporada() {
        return NumTemporada;
    }
    public Serie getSerie() {
        return Serie;
    }
    public Set<Capitulo> getCapitulos() {
        return Capitulos;
    }
    //#endregion

    //#region Setters
    public void setNumTemporada(int numTemporada) {
        NumTemporada = numTemporada;
    }
    public void setSerie(Serie serie) {
        Serie = serie;
    }
    public void setCapitulos(Set<Capitulo> capitulos) {
        Capitulos = capitulos;
    }
    //#endregion
    
    @Override
    public int hashCode(){
        return Objects.hash(NumTemporada, Serie, Capitulos);
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
        return ((this.Serie.equals(temporada.getSerie())) && (this.NumTemporada == temporada.getNumTemporada())
                && (this.Capitulos.equals(temporada.getCapitulos())));
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
