package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Gold")
public class Gold extends Serie{

    private final double Coste = 1.5;

    protected Gold(){
        super();
    }

    public Gold(String Titulo, String Sinopsis){
        super(Titulo, Sinopsis);
    }

    @Override
    public double getCoste() {
        return Coste;
    }
    
}
