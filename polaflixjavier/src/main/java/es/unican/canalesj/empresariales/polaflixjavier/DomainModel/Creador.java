package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.canalesj.empresariales.polaflixjavier.Views;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Creador {

    @Id
    @GeneratedValue
    private long id;
    
    @JsonView({Views.DescripcionSerie.class})
    private String nombre;
    @JsonView({Views.DescripcionSerie.class})
    private String apellido;

    protected Creador(){
        
    }
    
    public Creador(String nombre, String apellido){
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
        if(!(o instanceof Creador)){
            return false;
        }

        Creador creador = (Creador) o;
        return (this.nombre.equals(creador.getNombre())) && (this.apellido.equals(creador.getApellido()));
    }
}
