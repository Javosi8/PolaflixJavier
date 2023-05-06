package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.canalesj.empresariales.polaflixjavier.Views;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Actor {
    
    @Id
    @GeneratedValue
    private long id;
    
    @JsonView({Views.DescripcionSerie.class})
    private String nombre;
    @JsonView({Views.DescripcionSerie.class})
    private String apellido;

    protected Actor(){
        
    }

    public Actor(String nombre, String apellido){
        this.nombre = nombre;
        this.apellido = apellido;
    }

    //#region Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    //#endregion

    //#region Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    //#endregion
    
    @Override
    public int hashCode(){
        return Objects.hash(nombre, apellido);
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Actor)){
            return false;
        }

        Actor actor = (Actor) o;
        return (this.nombre.equals(actor.getNombre())) && (this.apellido.equals(actor.getApellido()));
    }
}
