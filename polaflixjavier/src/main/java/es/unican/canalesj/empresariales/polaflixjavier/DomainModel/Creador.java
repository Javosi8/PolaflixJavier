package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Creador {

    @Id
    @GeneratedValue
    private long id;
    
    private String Nombre;
    private String Apellido;
    
    public Creador(String Nombre, String Apellido){
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
        if(!(o instanceof Creador)){
            return false;
        }

        Creador creador = (Creador) o;
        return (this.Nombre.equals(creador.getNombre())) && (this.Apellido.equals(creador.getApellido()));
    }
}
