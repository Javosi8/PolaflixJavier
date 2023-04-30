package es.unican.canalesj.empresariales.polaflixjavier.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Factura;
import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Usuario;
import es.unican.canalesj.empresariales.polaflixjavier.Services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService us;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios(){
        List<Usuario> usuarios = us.getUsuarios();
        ResponseEntity<List<Usuario>> result;
        if(usuarios.isEmpty())
            result = ResponseEntity.badRequest().build();
        else
            result = ResponseEntity.ok(usuarios);
        
        return result;
    }

    @GetMapping(value = "/{nombreUsuario}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String nombreUsuario){
        Optional<Usuario> usuario = us.getUsuario(nombreUsuario);
        ResponseEntity<Usuario> result;
        if(usuario.isPresent())
            result = ResponseEntity.ok(usuario.get());
        else
            result = ResponseEntity.notFound().build();
        return result;
    }

    @PutMapping(value = "/{nombreUsuario}/agregarSeriePendientes/{idSerie}")
    public ResponseEntity<Usuario> agregarSeriePendientes(@PathVariable("nombreUsuario") String nombreUsuario, @PathVariable("idSerie") long idSerie){
        ResponseEntity<Usuario> result;
        Optional<Usuario> usuario = us.agregarSeriePendientes(nombreUsuario, idSerie);
        if(usuario.isPresent())
            result = ResponseEntity.ok(usuario.get());    
        else
            result = ResponseEntity.badRequest().build();
        return result;
    }

    @PutMapping(value = "/{nombreUsuario}/agregarSerieEmpezadas/{idSerie}")
    public ResponseEntity<Usuario> agregarSerieEmpezadas(@PathVariable("nombreUsuario") String nombreUsuario, @PathVariable("idSerie") long idSerie){
        ResponseEntity<Usuario> result;
        Optional<Usuario> usuario = us.agregarSerieEmpezadas(nombreUsuario, idSerie);
        if(usuario.isPresent())
            result = ResponseEntity.ok(usuario.get());
        else
            result = ResponseEntity.badRequest().build();
        return result;
    }

    @PutMapping(value = "/{nombreUsuario}/agregarSerieTerminadas/{idSerie}")
    public ResponseEntity<Usuario> agregarSerieTerminadas(@PathVariable("nombreUsuario") String nombreUsuario, @PathVariable("idSerie") long idSerie){
        ResponseEntity<Usuario> result;
        Optional<Usuario> usuario = us.agregarSerieTerminadas(nombreUsuario, idSerie);
        if(usuario.isPresent())
            result = ResponseEntity.ok(usuario.get());
        else
            result = ResponseEntity.badRequest().build();
        return result;
    }

    @PutMapping(value = "/{nombreUsuario}/verCapitulo")
    public ResponseEntity<Usuario> verCapitulo(@PathVariable String nombreUsuario, @RequestParam("idSerie") long idSerie,
                @RequestParam("idTemporada") int numTemporada, @RequestParam("idCapitulo") int numCapitulo){
        ResponseEntity<Usuario> result;
        Optional<Usuario> usuario = us.verCapitulo(nombreUsuario, idSerie, numTemporada, numCapitulo);
        if(usuario.isPresent())
            result = ResponseEntity.ok(usuario.get());
        else
            result = ResponseEntity.badRequest().build();
        return result;
    }

    @GetMapping(value = "/{nombreUsuario}/facturas")
    public ResponseEntity<List<Factura>> getFacturas(@PathVariable String nombreUsuario){
        ResponseEntity<List<Factura>> result;
        List<Factura> facturas = us.getFacturas(nombreUsuario);
        if(facturas.isEmpty())
            result = ResponseEntity.badRequest().build();
        else
            result = ResponseEntity.ok(facturas);
        return result;
    }
    
}
