package es.unican.canalesj.empresariales.polaflixjavier.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Serie;
import es.unican.canalesj.empresariales.polaflixjavier.Repositories.SerieRepository;

@Service
public class SerieService {
    
    @Autowired
    private SerieRepository sr;

    public List<Serie> getSeries(){
        return sr.findAll();
    }

    public Optional<Serie> getSerie(long idSerie){
        return sr.findById(idSerie);
    }

    public List<Serie> getSerieByInicial(char inicial){
        return sr.findByInicial(inicial);
    }
}
