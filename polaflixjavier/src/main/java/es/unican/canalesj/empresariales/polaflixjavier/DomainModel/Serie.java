package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorColumn(name="tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Serie {
    
    @Id
    @GeneratedValue
    private long Id;
    
    private String Titulo;    
    private char Inicial;
    private String Sinopsis;

    @OneToMany(mappedBy = "Serie")
    private Set<Temporada> Temporadas;

    @ManyToMany
    private Set<Actor> Actores;

    @ManyToMany
    private Set<Creador> Creadores;

    public Serie(String Titulo, String Sinopsis){
        this.Titulo = Titulo;
        this.Inicial = Titulo.charAt(0);
        this.Sinopsis = Sinopsis;

        Actores = new HashSet<Actor>();
        Creadores = new HashSet<Creador>();
        
        Temporadas = new TreeSet<Temporada>();
    }

    public Temporada getTemporadaById(int id){
        return Temporadas.stream().filter(t -> t.getNumTemporada() == id).findFirst().get();
    }

    //#region Getters
    public String getTitulo() {
        return Titulo;
    }
    public char getInicial() {
        return Inicial;
    }
    public String getSinopsis() {
        return Sinopsis;
    }
    public Set<Actor> getActores() {
        return Actores;
    }
    public Set<Creador> getCreadores() {
        return Creadores;
    }
    public Set<Temporada> getTemporadas() {
        return Temporadas;
    }
    public abstract double getCoste();
    //#endregion

    //#region Setters
    public void setTitulo(String titulo) {
        Titulo = titulo;
        Inicial = titulo.charAt(0);
    }
    public void setSinopsis(String sinopsis) {
        Sinopsis = sinopsis;
    }
    public void setActores(Set<Actor> actores) {
        Actores = actores;
    }
    public void setCreadores(Set<Creador> creadores) {
        Creadores = creadores;
    }
    public void setTemporadas(Set<Temporada> temporadas) {
        Temporadas = temporadas;
    }
    //#endregion

    @Override
    public int hashCode(){
        return Objects.hash(Titulo, Inicial, Sinopsis, Actores, Creadores, Temporadas);
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }

        if(!(o instanceof Serie)){
            return false;
        }

        Serie serie = (Serie)o;
        return (this.Titulo.equals(serie.getTitulo()) && (this.Sinopsis.equals(serie.getSinopsis()))
        && (this.Actores.equals(serie.getActores())) && (this.Creadores.equals(serie.getCreadores()))
        && (this.Temporadas.equals(serie.getTemporadas())));
    }
}
