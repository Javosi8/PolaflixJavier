package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Capitulo implements Comparable<Capitulo>{
    
    @Id
    @GeneratedValue
    private long id;

    private int numCapitulo;
    private int duracion;
    private String descripcion;
    private String titulo;
    private String enlace;

    @ManyToOne
    private Temporada temporada;

    protected Capitulo(){
        
    }

    public Capitulo(int numCapitulo, String titulo, String descripcion, int duracion, String enlace, Temporada temporada){
        this.numCapitulo = numCapitulo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.enlace = enlace;
        this.temporada = temporada;
    }
    
    //#region Getters
    public int getDuracion() {
        return duracion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getNumCapitulo() {
        return numCapitulo;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getEnlace() {
        return enlace;
    }
    public Temporada getTemporada() {
        return temporada;
    }
    //#endregion
        
    //#region Setters
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setNumCapitulo(int numCapitulo) {
        this.numCapitulo = numCapitulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }
    
    //#endregion

    @Override
    public int hashCode(){
        return Objects.hash(numCapitulo, titulo, enlace, temporada);
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }

        if(!(o instanceof Capitulo)){
            return false;
        }

        Capitulo capitulo = (Capitulo)o;
        return ((this.numCapitulo == capitulo.getNumCapitulo()) && (this.titulo.equals(capitulo.getTitulo()))
        && (this.temporada.equals(capitulo.getTemporada())) && (this.enlace.equals(capitulo.getEnlace())));
    }

    @Override
    public int compareTo(Capitulo capitulo){
        if(this.numCapitulo > capitulo.getNumCapitulo()){
            return 1;
        }
        if(this.numCapitulo < capitulo.getNumCapitulo()){
            return -1;
        }
        return 0;
    }
}
