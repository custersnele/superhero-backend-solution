package be.pxl.superhero.service.impl;

import be.pxl.superhero.api.SuperheroDTO;
import be.pxl.superhero.builder.SuperheroBuilder;
import be.pxl.superhero.exception.ResourceNotFoundException;
import be.pxl.superhero.repository.SuperheroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static be.pxl.superhero.builder.SuperheroBuilder.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuperheroServiceImplFindSuperheroByIdTest {

	@Mock
	private SuperheroRepository superheroRepository;

	@InjectMocks
	private SuperheroServiceImpl superheroService;

	@Test
	public void returnsSuperheroDTO() {
		when(superheroRepository.findById(ID)).thenReturn(Optional.of(aSuperhero().build()));
		SuperheroDTO superhero = superheroService.findSuperheroById(ID);
		assertNotNull(superhero);
		assertEquals(ID, superhero.getId());
		assertEquals(FIRSTNAME, superhero.getFirstName());
		assertEquals(LASTNAME, superhero.getLastName());
		assertEquals(SUPERHERO_NAME, superhero.getSuperheroName());
	}

	@Test
	public void throwsResourceNotFoundExceptionWhenNoSuperheroWithGivenId() {
		when(superheroRepository.findById(ID)).thenReturn(Optional.empty());
		ResourceNotFoundException resourceNotFoundException =
				Assertions.assertThrows(ResourceNotFoundException.class, () -> superheroService.findSuperheroById(ID));
		assertEquals("Superhero not found with ID : '" + ID +"'", resourceNotFoundException.getMessage());
	}
}
