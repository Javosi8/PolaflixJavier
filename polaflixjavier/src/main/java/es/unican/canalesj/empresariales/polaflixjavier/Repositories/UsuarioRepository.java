package es.unican.canalesj.empresariales.polaflixjavier.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
}
