package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.canalesj.empresariales.polaflixjavier.Views;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Estandar")
public class Estandar extends Serie{
    
    @JsonView({Views.DescripcionSerie.class})
    private final double coste = 0.5;

    protected Estandar(){
        super();
    }

    public Estandar(String titulo, String sinopsis){
        super(titulo, sinopsis);
    }

    @Override
    public double getCoste() {
        return coste;
    }
}
