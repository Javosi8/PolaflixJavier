package es.unican.canalesj.empresariales.polaflixjavier.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Factura;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Serie;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Usuario;
import es.unican.canalesj.empresariales.polaflixjavier.Repositories.SerieRepository;
import es.unican.canalesj.empresariales.polaflixjavier.Repositories.UsuarioRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @PersistenceUnit
    EntityManagerFactory emf;
    
    @Autowired
    private UsuarioRepository ur;
    @Autowired
    private SerieRepository sr;

    public Optional<Usuario> getUsuario(String nombreUsuario){
        return ur.findById(nombreUsuario);
    }

    @Transactional
    public Optional<Usuario> agregarSeriePendientes(String nombreUsuario, long idSerie){
        Optional<Usuario> usuario = ur.findById(nombreUsuario);
        Optional<Serie> serie = sr.findById(idSerie);
        if(usuario.isPresent() && serie.isPresent())
            usuario.get().agregarSerieAPendientes(serie.get());
        return usuario;
    }

    @Transactional
    public Optional<Usuario> verCapitulo(String nombreUsuario, long idSerie, int idTemporada, int idCapitulo){
        Optional<Usuario> usuario = ur.findById(nombreUsuario);
        Optional<Serie> serie = sr.findById(idSerie);
        if(usuario.isPresent() && serie.isPresent())
            usuario.get().verCapitulo(serie.get().getTemporadaById(idTemporada).getCapituloById(idCapitulo));
        return usuario;
    }

    public List<Factura> getFacturas(String nombreUsuario){
        List<Factura> facturas = new ArrayList<>();
        Optional<Usuario> usuario = ur.findById(nombreUsuario);
        if(usuario.isPresent()){
            facturas = new ArrayList<>(usuario.get().getFacturas());
        }
        return facturas;
    }
}
