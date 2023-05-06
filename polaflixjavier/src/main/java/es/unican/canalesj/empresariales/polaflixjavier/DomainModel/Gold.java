package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.canalesj.empresariales.polaflixjavier.Views;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Gold")
public class Gold extends Serie{

    @JsonView({Views.DescripcionSerie.class})
    private final double coste = 1.5;

    protected Gold(){
        super();
    }

    public Gold(String titulo, String sinopsis){
        super(titulo, sinopsis);
    }

    @Override
    public double getCoste() {
        return coste;
    }
    
}
