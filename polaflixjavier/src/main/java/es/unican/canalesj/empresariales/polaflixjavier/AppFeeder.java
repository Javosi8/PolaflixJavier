package es.unican.canalesj.empresariales.polaflixjavier;

import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Actor;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Capitulo;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Creador;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Estandar;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Gold;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Serie;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Silver;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Temporada;
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

        // Gestión para la serie 1
        Serie s1 = new Estandar("Los Simpson", "Una familia amarilla");
        Temporada t1S1 = new Temporada(1, s1);
        Temporada t2S1 = new Temporada(2, s1);
        Temporada t3S1 = new Temporada(3, s1);
        
        SortedSet<Temporada> temporadasS1 = new TreeSet<>();
        SortedSet<Capitulo> capitulosT1S1 = new TreeSet<>();
        SortedSet<Capitulo> capitulosT2S1 = new TreeSet<>();
        SortedSet<Capitulo> capitulosT3S1 = new TreeSet<>();

        capitulosT1S1.add(new Capitulo(1, "Primer capitulo", "Capitulo numero 1", 90, "http://verCapitulo.com", t1S1));
        capitulosT1S1.add(new Capitulo(2, "Segundo capitulo", "Capitulo numero 2", 90, "http://verCapitulo.com", t1S1));
        capitulosT1S1.add(new Capitulo(3, "Tercer capitulo", "Capitulo numero 3", 90, "http://verCapitulo.com", t1S1));

        capitulosT2S1.add(new Capitulo(1, "Primer capitulo", "Capitulo numero 1", 90, "http://verCapitulo.com", t2S1));
        capitulosT2S1.add(new Capitulo(2, "Segundo capitulo", "Capitulo numero 2", 90, "http://verCapitulo.com", t2S1));
        capitulosT2S1.add(new Capitulo(3, "Tercer capitulo", "Capitulo numero 3", 90, "http://verCapitulo.com", t2S1));

        capitulosT3S1.add(new Capitulo(1, "Primer capitulo", "Capitulo numero 1", 90, "http://verCapitulo.com", t3S1));
        capitulosT3S1.add(new Capitulo(2, "Segundo capitulo", "Capitulo numero 2", 90, "http://verCapitulo.com", t3S1));
        capitulosT3S1.add(new Capitulo(3, "Tercer capitulo", "Capitulo numero 3", 90, "http://verCapitulo.com", t3S1));

        t1S1.setCapitulos(capitulosT1S1);
        t2S1.setCapitulos(capitulosT2S1);
        t3S1.setCapitulos(capitulosT3S1);

        temporadasS1.add(t1S1);
        temporadasS1.add(t2S1);
        temporadasS1.add(t3S1);
        s1.setTemporadas(temporadasS1);

        Creador c1s1 = new Creador("Pedro", "Almodovar");
        Creador c2s1 = new Creador("Steven", "Spielberg");
        Actor a1s1 = new Actor("Brad", "Pitt");
        Actor a2s1 = new Actor("Angelina", "Jolie");
        HashSet<Creador> creadoresS1 = new HashSet<>();
        HashSet<Actor> actoresS1 = new HashSet<>();

        creadoresS1.add(c1s1);
        creadoresS1.add(c2s1);
        actoresS1.add(a1s1);
        actoresS1.add(a2s1);

        s1.setCreadores(creadoresS1);
        s1.setActores(actoresS1);

        // Gestión para la serie 2
        Serie s2 = new Silver("Mr Robot", "La vida de un hacker");
        Temporada t1S2 = new Temporada(1, s2);
        Temporada t2S2 = new Temporada(2, s2);
        Temporada t3S2 = new Temporada(3, s2);
        SortedSet<Temporada> temporadasS2 = new TreeSet<>();
        SortedSet<Capitulo> capitulosT1S2 = new TreeSet<>();
        SortedSet<Capitulo> capitulosT2S2 = new TreeSet<>();
        SortedSet<Capitulo> capitulosT3S2 = new TreeSet<>();

        capitulosT1S2.add(new Capitulo(1, "Primer capitulo", "Capitulo numero 1", 90, "http://verCapitulo.com", t1S2));
        capitulosT1S2.add(new Capitulo(2, "Segundo capitulo", "Capitulo numero 2", 90, "http://verCapitulo.com", t1S2));
        capitulosT1S2.add(new Capitulo(3, "Tercer capitulo", "Capitulo numero 3", 90, "http://verCapitulo.com", t1S2));

        capitulosT2S2.add(new Capitulo(1, "Primer capitulo", "Capitulo numero 1", 90, "http://verCapitulo.com", t2S2));
        capitulosT2S2.add(new Capitulo(2, "Segundo capitulo", "Capitulo numero 2", 90, "http://verCapitulo.com", t2S2));
        capitulosT2S2.add(new Capitulo(3, "Tercer capitulo", "Capitulo numero 3", 90, "http://verCapitulo.com", t2S2));

        capitulosT3S2.add(new Capitulo(1, "Primer capitulo", "Capitulo numero 1", 90, "http://verCapitulo.com", t3S2));
        capitulosT3S2.add(new Capitulo(2, "Segundo capitulo", "Capitulo numero 2", 90, "http://verCapitulo.com", t3S2));
        capitulosT3S2.add(new Capitulo(3, "Tercer capitulo", "Capitulo numero 3", 90, "http://verCapitulo.com", t3S2));

        t1S2.setCapitulos(capitulosT1S2);
        t2S2.setCapitulos(capitulosT2S2);
        t3S2.setCapitulos(capitulosT3S2);

        temporadasS2.add(t1S2);
        temporadasS2.add(t2S2);
        temporadasS2.add(t3S2);
        s2.setTemporadas(temporadasS2);

        Creador c1s2 = new Creador("Pedro", "Almodovar");
        Creador c2s2 = new Creador("Steven", "Spielberg");
        Actor a1s2 = new Actor("Brad", "Pitt");
        Actor a2s2 = new Actor("Angelina", "Jolie");
        HashSet<Creador> creadoresS2 = new HashSet<>();
        HashSet<Actor> actoresS2 = new HashSet<>();

        creadoresS2.add(c1s2);
        creadoresS2.add(c2s2);
        actoresS2.add(a1s2);
        actoresS2.add(a2s2);

        s2.setCreadores(creadoresS2);
        s2.setActores(actoresS2);

        // Gestionamos la serie 3
        Serie s3 = new Gold("Peaky Blinders", "La mafia inglesa");
        Temporada t1S3 = new Temporada(1, s3);
        Temporada t2S3 = new Temporada(2, s3);
        Temporada t3S3 = new Temporada(3, s3);
        SortedSet<Temporada> temporadasS3 = new TreeSet<>();
        SortedSet<Capitulo> capitulosT1S3 = new TreeSet<>();
        SortedSet<Capitulo> capitulosT2S3 = new TreeSet<>();
        SortedSet<Capitulo> capitulosT3S3 = new TreeSet<>();

        capitulosT1S3.add(new Capitulo(1, "Primer capitulo", "Capitulo numero 1", 90, "http://verCapitulo.com", t1S3));
        capitulosT1S3.add(new Capitulo(2, "Segundo capitulo", "Capitulo numero 2", 90, "http://verCapitulo.com", t1S3));
        capitulosT1S3.add(new Capitulo(3, "Tercer capitulo", "Capitulo numero 3", 90, "http://verCapitulo.com", t1S3));

        capitulosT2S3.add(new Capitulo(1, "Primer capitulo", "Capitulo numero 1", 90, "http://verCapitulo.com", t2S3));
        capitulosT2S3.add(new Capitulo(2, "Segundo capitulo", "Capitulo numero 2", 90, "http://verCapitulo.com", t2S3));
        capitulosT2S3.add(new Capitulo(3, "Tercer capitulo", "Capitulo numero 3", 90, "http://verCapitulo.com", t2S3));

        capitulosT3S3.add(new Capitulo(1, "Primer capitulo", "Capitulo numero 1", 90, "http://verCapitulo.com", t3S3));
        capitulosT3S3.add(new Capitulo(2, "Segundo capitulo", "Capitulo numero 2", 90, "http://verCapitulo.com", t3S3));
        capitulosT3S3.add(new Capitulo(3, "Tercer capitulo", "Capitulo numero 3", 90, "http://verCapitulo.com", t3S3));

        t1S3.setCapitulos(capitulosT1S3);
        t2S3.setCapitulos(capitulosT2S3);
        t3S3.setCapitulos(capitulosT3S3);

        temporadasS3.add(t1S3);
        temporadasS3.add(t2S3);
        temporadasS3.add(t3S3);
        s3.setTemporadas(temporadasS3);

        Creador c1s3 = new Creador("Pedro", "Almodovar");
        Creador c2s3 = new Creador("Steven", "Spielberg");
        Actor a1s3 = new Actor("Brad", "Pitt");
        Actor a2s3 = new Actor("Angelina", "Jolie");
        HashSet<Creador> creadoresS3 = new HashSet<>();
        HashSet<Actor> actoresS3 = new HashSet<>();

        creadoresS3.add(c1s3);
        creadoresS3.add(c2s3);
        actoresS3.add(a1s3);
        actoresS3.add(a2s3);

        s3.setCreadores(creadoresS3);
        s3.setActores(actoresS3);

        sr.save(s1);
        sr.save(s2);
        sr.save(s3);
    }

}
