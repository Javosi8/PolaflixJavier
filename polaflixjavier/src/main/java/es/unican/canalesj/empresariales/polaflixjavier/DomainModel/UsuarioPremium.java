package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Premium")
public class UsuarioPremium extends Usuario{
    
    private final double cuota = 20.0;

    protected UsuarioPremium(){
        super();
    }

    public UsuarioPremium(String username, String password, String iban){
        super(username, password, iban);
    }

    //#region Getters
    public double getCuota() {
        return cuota;
    }
    //#endregion
    
}
