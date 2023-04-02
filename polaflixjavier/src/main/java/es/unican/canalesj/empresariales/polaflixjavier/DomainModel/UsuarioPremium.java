package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Premium")
public class UsuarioPremium extends Usuario{
    
    private final double Cuota = 20.0;

    protected UsuarioPremium(){
        super();
    }

    public UsuarioPremium(String Username, String Password, String IBAN){
        super(Username, Password, IBAN);
    }

    //#region Getters
    public double getCuota() {
        return Cuota;
    }
    //#endregion
    
}
