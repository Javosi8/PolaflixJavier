package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Gold")
public class Gold extends Serie{

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
