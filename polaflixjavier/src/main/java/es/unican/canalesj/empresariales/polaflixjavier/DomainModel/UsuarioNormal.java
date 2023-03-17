package es.unican.canalesj.empresariales.polaflixjavier.DomainModel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Normal")
public class UsuarioNormal extends Usuario {
    
    public UsuarioNormal(String Username, String Password, String IBAN){
        super(Username, Password, IBAN);
    }

}
