package es.unican.canalesj.empresariales.polaflixjavier.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.canalesj.empresariales.polaflixjavier.Views;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Factura;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Usuario;
import es.unican.canalesj.empresariales.polaflixjavier.Services.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService us;

    @GetMapping(value = "/{nombreUsuario}")
    @JsonView({Views.DescripcionUsuario.class})
    public ResponseEntity<Usuario> getUsuario(@PathVariable String nombreUsuario){
        try{
            Optional<Usuario> usuario = us.getUsuario(nombreUsuario);
            ResponseEntity<Usuario> result;
            if(usuario.isPresent())
                result = ResponseEntity.ok(usuario.get());
            else
                result = ResponseEntity.notFound().build();
            return result;
        }catch(Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{nombreUsuario}/pendientes/{idSerie}")
    @JsonView({Views.DescripcionUsuario.class})
    public ResponseEntity<Usuario> agregarSeriePendientes(@PathVariable("nombreUsuario") String nombreUsuario, @PathVariable("idSerie") long idSerie){
        ResponseEntity<Usuario> result;
        Optional<Usuario> usuario = us.agregarSeriePendientes(nombreUsuario, idSerie);
        if(usuario.isPresent())
            result = ResponseEntity.ok(usuario.get());    
        else
            result = ResponseEntity.internalServerError().build();
        return result;
    }

    @PutMapping(value = "/{nombreUsuario}/verCapitulo")
    @JsonView({Views.DescripcionUsuario.class})
    public ResponseEntity<Usuario> verCapitulo(@PathVariable String nombreUsuario, @RequestParam("idSerie") long idSerie,
                @RequestParam("idTemporada") int numTemporada, @RequestParam("idCapitulo") int numCapitulo){
        ResponseEntity<Usuario> result;
        Optional<Usuario> usuario = us.verCapitulo(nombreUsuario, idSerie, numTemporada, numCapitulo);
        if(usuario.isPresent())
            result = ResponseEntity.ok(usuario.get());
        else
            result = ResponseEntity.internalServerError().build();
        return result;
    }

    @GetMapping(value = "/{nombreUsuario}/facturas")
    @JsonView({Views.DescripcionFactura.class})
    public ResponseEntity<List<Factura>> getFacturas(@PathVariable String nombreUsuario){
        try{
            List<Factura> facturas = us.getFacturas(nombreUsuario);
            return ResponseEntity.ok(facturas);
        }catch(Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }
    
}
