package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Estandar")
public class Estandar extends Serie{
    
    private final double Coste = 0.5;

    protected Estandar(){
        super();
    }

    public Estandar(String Titulo, String Sinopsis){
        super(Titulo, Sinopsis);
    }

    @Override
    public double getCoste() {
        return Coste;
    }
}
