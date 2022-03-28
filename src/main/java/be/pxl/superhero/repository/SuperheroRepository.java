package be.pxl.superhero.repository;

import be.pxl.superhero.domain.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

	Optional<Superhero> findSuperheroBySuperheroName(String superheroName);
}
