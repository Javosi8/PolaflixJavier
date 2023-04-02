package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Actor {
    
    @Id
    @GeneratedValue
    private long Id;
    
    private String Nombre;
    private String Apellido;

    protected Actor(){
        
    }

    public Actor(String Nombre, String Apellido){
        this.Nombre = Nombre;
        this.Apellido = Apellido;
    }

    //#region Getters
    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }
    //#endregion

    //#region Setters
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }
    //#endregion
    
    @Override
    public int hashCode(){
        return Objects.hash(Nombre, Apellido);
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
        return (this.Nombre.equals(actor.getNombre())) && (this.Apellido.equals(actor.getApellido()));
    }
}
