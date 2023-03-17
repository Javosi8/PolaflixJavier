package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Silver")
public class Silver extends Serie{
    
    private final double Coste = 0.75;
    
    public Silver(String Titulo, String Sinopsis){
        super(Titulo, Sinopsis);
    }

    @Override
    public double getCoste() {
        return Coste;
    }
    
}
