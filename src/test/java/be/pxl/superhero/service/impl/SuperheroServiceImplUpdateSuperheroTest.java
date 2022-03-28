package be.pxl.superhero.service.impl;

import be.pxl.superhero.api.SuperheroDTO;
import be.pxl.superhero.api.SuperheroRequest;
import be.pxl.superhero.builder.SuperheroBuilder;
import be.pxl.superhero.domain.Superhero;
import be.pxl.superhero.exception.ResourceNotFoundException;
import be.pxl.superhero.repository.SuperheroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuperheroServiceImplUpdateSuperheroTest {

	private static final Long SUPERHERO_ID = 15L;
	@Mock
	private SuperheroRepository superheroRepository;

	@InjectMocks
	private SuperheroServiceImpl superheroService;

	@Test
	public void throwsResourceNotFoundExceptionWhenThereIsNotSuperheroWithGivenId() {
		when(superheroRepository.findById(SUPERHERO_ID)).thenReturn(Optional.empty());
		SuperheroRequest superheroRequest = new SuperheroRequest("Bruce", "Wayne", "Batman");
		ResourceNotFoundException ex =
				assertThrows(ResourceNotFoundException.class, () -> superheroService.updateSuperhero(SUPERHERO_ID, superheroRequest));
		assertEquals("Superhero not found with id : '" + SUPERHERO_ID + "'", ex.getMessage());
	}

	@Test
	public void updatesAnExistingSuperhero() {
		Superhero superhero = SuperheroBuilder.aSuperhero()
				.withId(SUPERHERO_ID)
				.build();
		when(superheroRepository.findById(SUPERHERO_ID)).thenReturn(Optional.of(superhero));
		when(superheroRepository.save(any(Superhero.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
		SuperheroRequest superheroRequest = new SuperheroRequest("Bruce", "Wayne", "Batman");
		SuperheroDTO superheroDTO = superheroService.updateSuperhero(SUPERHERO_ID, superheroRequest);
		assertEquals(SUPERHERO_ID, superheroDTO.getId());
		assertEquals("Bruce", superheroDTO.getFirstName());
		assertEquals("Wayne", superheroDTO.getLastName());
		assertEquals("Batman", superheroDTO.getSuperheroName());
	}
}
