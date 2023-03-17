package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@DiscriminatorColumn(name="tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Serie {
    
    @Id
    private String Titulo;    
    private char Inicial;
    private String Sinopsis;

    private Set<Temporada> Temporadas;

    private Set<Actor> Actores;
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
    //#endregion

    @Override
    public int hashCode(){
        return Objects.hash(Titulo, Inicial, Sinopsis);
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
        return (this.Titulo.equals(serie.Titulo))   ;
    }
}
