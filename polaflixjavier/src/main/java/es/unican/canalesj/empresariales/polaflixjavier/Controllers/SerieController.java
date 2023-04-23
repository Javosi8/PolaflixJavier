package es.unican.canalesj.empresariales.polaflixjavier.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Serie;
import es.unican.canalesj.empresariales.polaflixjavier.Services.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {
    
    @Autowired
    private SerieService ss;

    @GetMapping
    public ResponseEntity<List<Serie>> getSeries(){
        List<Serie> series = ss.getSeries();
        ResponseEntity<List<Serie>> result;
        if(series.isEmpty())
            result = ResponseEntity.notFound().build();
        else
            result = ResponseEntity.ok(series);
        return result;
    }

    @GetMapping(value = "/{idSerie}")
    public ResponseEntity<Serie> getSerie(@PathVariable long idSerie){
        Optional<Serie> serie = ss.getSerie(idSerie);
        ResponseEntity<Serie> result;
        if(serie.isPresent())
            result = ResponseEntity.ok(serie.get());
        else
            result = ResponseEntity.notFound().build();
        return result;
    }

    @GetMapping(value = "/inicial/{inicial}")
    public ResponseEntity<List<Serie>> getSerieByInicial(@PathVariable char inicial){
        List<Serie> series = ss.getSerieByInicial(inicial);
        ResponseEntity<List<Serie>> result;
        if(series.isEmpty())
            result = ResponseEntity.notFound().build();
        else
            result = ResponseEntity.ok(series);
        return result;
    }

}
