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
    private long Id;

    private int NumCapitulo;
    private int Duracion;
    private String Descripcion;
    private String Titulo;
    private String Enlace;

    @ManyToOne
    private Temporada Temporada;

    protected Capitulo(){
        
    }

    public Capitulo(int NumCapitulo, String Titulo, String Descripcion, int Duracion, String Enlace, Temporada Temporada){
        this.NumCapitulo = NumCapitulo;
        this.Titulo = Titulo;
        this.Descripcion = Descripcion;
        this.Duracion = Duracion;
        this.Enlace = Enlace;
        this.Temporada = Temporada;
    }
    
    //#region Getters
    public int getDuracion() {
        return Duracion;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public int getNumCapitulo() {
        return NumCapitulo;
    }
    public String getTitulo() {
        return Titulo;
    }
    public String getEnlace() {
        return Enlace;
    }
    public Temporada getTemporada() {
        return Temporada;
    }
    //#endregion
        
    //#region Setters
    public void setDuracion(int duracion) {
        this.Duracion = duracion;
    }
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    public void setNumCapitulo(int numCapitulo) {
        NumCapitulo = numCapitulo;
    }
    public void setTitulo(String titulo) {
        Titulo = titulo;
    }
    public void setEnlace(String enlace) {
        Enlace = enlace;
    }
    public void setTemporada(Temporada temporada) {
        Temporada = temporada;
    }
    
    //#endregion

    @Override
    public int hashCode(){
        return Objects.hash(NumCapitulo, Duracion, Descripcion, Titulo, Enlace, Temporada);
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
        return ((this.NumCapitulo == capitulo.getNumCapitulo()) && (this.Titulo.equals(capitulo.getTitulo()))
        && (this.Duracion == capitulo.getDuracion()) && (this.Temporada.equals(capitulo.getTemporada()))
        && (this.Enlace.equals(capitulo.getEnlace())) && (this.Descripcion.equals(capitulo.getDescripcion())));
    }

    @Override
    public int compareTo(Capitulo capitulo){
        if(this.NumCapitulo > capitulo.getNumCapitulo()){
            return 1;
        }
        if(this.NumCapitulo < capitulo.getNumCapitulo()){
            return -1;
        }
        return 0;
    }
}
