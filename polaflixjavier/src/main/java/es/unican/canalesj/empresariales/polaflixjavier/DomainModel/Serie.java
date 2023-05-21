package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.canalesj.empresariales.polaflixjavier.Views;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorColumn(name="Tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Serie {
    
    @Id
    @GeneratedValue
    @JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
    private long id;
    
    @JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
    private String titulo;    
    @JsonView({Views.DescripcionSerie.class})
    private char inicial;
    @JsonView({Views.DescripcionSerie.class})
    private String sinopsis;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonView({Views.DescripcionSerie.class})
    private SortedSet<Temporada> temporadas;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonView({Views.DescripcionSerie.class})
    private Set<Actor> actores;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonView({Views.DescripcionSerie.class})
    private Set<Creador> creadores;

    protected Serie(){
        
    }

    public Serie(String titulo, String sinopsis){
        this.titulo = titulo;
        this.inicial = titulo.charAt(0);
        this.sinopsis = sinopsis;

        actores = new HashSet<Actor>();
        creadores = new HashSet<Creador>();
        
        temporadas = new TreeSet<Temporada>();
    }

    public Temporada getTemporadaById(int id){
        return temporadas.stream().filter(t -> t.getNumTemporada() == id).findFirst().get();
    }

    //#region Getters
    public String getTitulo() {
        return titulo;
    }
    public char getInicial() {
        return inicial;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public Set<Actor> getActores() {
        return actores;
    }
    public Set<Creador> getCreadores() {
        return creadores;
    }
    public SortedSet<Temporada> getTemporadas() {
        return temporadas;
    }
    public abstract double getCoste();
    //#endregion

    //#region Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
        inicial = titulo.charAt(0);
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    public void setActores(Set<Actor> actores) {
        this.actores = actores;
    }
    public void setCreadores(Set<Creador> creadores) {
        this.creadores = creadores;
    }
    public void setTemporadas(SortedSet<Temporada> temporadas) {
        this.temporadas = temporadas;
    }
    //#endregion

    @Override
    public int hashCode(){
        return Objects.hash(titulo, inicial, sinopsis);
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
        return (this.titulo.equals(serie.getTitulo()) && (this.sinopsis.equals(serie.getSinopsis()))
        && (this.inicial == serie.getInicial()));
    }
}
