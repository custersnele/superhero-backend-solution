package be.pxl.superhero.repository;

import be.pxl.superhero.builder.SuperheroBuilder;
import be.pxl.superhero.domain.Superhero;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class SuperheroRepositoryTest {

	private static final String SUPERHERO_NAME = "Superman";

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private SuperheroRepository superheroRepository;

	private Superhero superhero = SuperheroBuilder.aSuperhero().build();

	@BeforeEach
	public void init() {
		superheroRepository.save(superhero);
		entityManager.flush();
		entityManager.clear();
	}

	@Test
	public void returnsSuperheroWithGivenSuperheroName() {
		Optional<Superhero> superheroUnderTest = superheroRepository.findSuperheroBySuperheroName(SUPERHERO_NAME);

		assertTrue(superheroUnderTest.isPresent());
		assertEquals(SUPERHERO_NAME, superheroUnderTest.get().getSuperheroName());

	}

	@Test
	public void returnsEmptyOptionalWhenNoInstituteWithGivenName() {
		Optional<Superhero> superheroUnderTest = superheroRepository.findSuperheroBySuperheroName("Robin Hood");

		assertTrue(superheroUnderTest.isEmpty());
	}
}
