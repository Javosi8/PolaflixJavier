package es.unican.canalesj.empresariales.polaflixjavier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Estandar;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Gold;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Serie;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Silver;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Usuario;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.UsuarioNormal;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.UsuarioPremium;
import es.unican.canalesj.empresariales.polaflixjavier.Repositories.SerieRepository;
import es.unican.canalesj.empresariales.polaflixjavier.Repositories.UsuarioRepository;

@Component
public class AppFeeder implements CommandLineRunner{
    
    @Autowired
    protected UsuarioRepository ur;
    @Autowired
    protected SerieRepository sr;

    @Override
    public void run(String... args){
        feedUsuarios();
        feedSeries();

        System.out.println("Datos añadidos correctamente en el AppFeeder");
    }

    public void feedUsuarios(){
        Usuario u1 = new UsuarioNormal("Paco", "Perez", "123456");
        Usuario u2 = new UsuarioPremium("Javier", "Canales", "88888888");
        ur.save(u1);
        ur.save(u2);
    }

    public void feedSeries(){
        Serie s1 = new Estandar("Los Simpson", "Una familia amarilla");
        Serie s2 = new Silver("Mr Robot", "La vida de un hacker");
        Serie s3 = new Gold("Peaky Blinders", "La mafia inglesa");
        sr.save(s1);
        sr.save(s2);
        sr.save(s3);
    }

}
