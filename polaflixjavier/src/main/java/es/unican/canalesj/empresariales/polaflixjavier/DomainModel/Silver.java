package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Silver")
public class Silver extends Serie{
    
    private final double coste = 0.75;

    protected Silver(){
        super();
    }
    
    public Silver(String titulo, String sinopsis){
        super(titulo, sinopsis);
    }

    @Override
    public double getCoste() {
        return coste;
    }
    
}
