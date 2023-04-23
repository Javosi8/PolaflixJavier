package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Normal")
public class UsuarioNormal extends Usuario {
    
    protected UsuarioNormal(){
        super();
    }

    public UsuarioNormal(String username, String password, String iban){
        super(username, password, iban);
    }

}
