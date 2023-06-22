package es.unican.canalesj.empresariales.polaflixjavier.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.canalesj.empresariales.polaflixjavier.Views;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Serie;
import es.unican.canalesj.empresariales.polaflixjavier.Services.SerieService;

@RestController
@CrossOrigin("*")
@RequestMapping("/series")
public class SerieController {
    
    @Autowired
    private SerieService ss;

    @GetMapping
    @JsonView({Views.DescripcionSerie.class})
    public ResponseEntity<List<Serie>> getSeries(){
        try{
            List<Serie> series = ss.getSeries();
            return ResponseEntity.ok(series);
        }catch(Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{idSerie}")
    @JsonView({Views.DescripcionSerie.class})
    public ResponseEntity<Serie> getSerie(@PathVariable long idSerie){
        try{
            Optional<Serie> serie = ss.getSerie(idSerie);
            ResponseEntity<Serie> result;
            if(serie.isPresent())
                result = ResponseEntity.ok(serie.get());
            else
                result = ResponseEntity.notFound().build();
            return result;
        }catch(Exception ex){
            return ResponseEntity.internalServerError().build();
        }
        
    }

    @GetMapping(params = "inicial")
    @JsonView({Views.DescripcionSerie.class})
    public ResponseEntity<List<Serie>> getSerieByInicial(@RequestParam char inicial){
        try{
            List<Serie> series = ss.getSerieByInicial(inicial);
            return ResponseEntity.ok(series);
        }catch(Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(params = "titulo")
    @JsonView({Views.DescripcionSerie.class})
    public ResponseEntity<List<Serie>> getSerieByTitulo(@RequestParam String titulo){
        try{
            List<Serie> series = ss.getSerieByTitulo(titulo);
            return ResponseEntity.ok(series);
        }catch(Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

}
