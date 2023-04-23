package es.unican.canalesj.empresariales.polaflixjavier.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.canalesj.empresariales.polaflixjavier.DomainModel.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    public List<Serie> findByInicial(char inicial);
}
