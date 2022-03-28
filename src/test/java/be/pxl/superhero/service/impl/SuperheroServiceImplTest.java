package be.pxl.superhero.service.impl;

import be.pxl.superhero.api.SuperheroDTO;
import be.pxl.superhero.api.SuperheroRequest;
import be.pxl.superhero.builder.SuperheroBuilder;
import be.pxl.superhero.domain.Superhero;
import be.pxl.superhero.repository.SuperheroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LONG_STREAM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuperheroServiceImplTest {

	private static final String SUPERHERO_NAME = "Superman";
	private static final String FIRST_NAME = "Clark";
	private static final String LAST_NAME = "Kent";

	@Mock
	private SuperheroRepository superheroRepository;

	@Captor
	private ArgumentCaptor<Superhero> superheroCaptor;

	@InjectMocks
	private SuperheroServiceImpl superheroService;

	private final Superhero superhero = SuperheroBuilder.aSuperhero()
			.withFirstName(FIRST_NAME)
			.withLastName(LAST_NAME)
			.withSuperheroName(SUPERHERO_NAME)
			.build();

	@Test
	public void throwsValidationExceptionIfSuperheroNameIsNotUnique() {
		when(superheroRepository.findSuperheroBySuperheroName(SUPERHERO_NAME)).thenReturn(Optional.of(superhero));
		SuperheroRequest request = new SuperheroRequest(FIRST_NAME, LAST_NAME, SUPERHERO_NAME);
		ValidationException validationException =
				assertThrows(ValidationException.class, () -> superheroService.createSuperhero(request));
		assertEquals("This superhero name is already taken.", validationException.getMessage());
	}

	@Test
	public void savesSuperheroIfSuperheroNameIsUnique() {
		when(superheroRepository.findSuperheroBySuperheroName(SUPERHERO_NAME)).thenReturn(Optional.empty());
		when(superheroRepository.save(Mockito.any(Superhero.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
		SuperheroRequest request = new SuperheroRequest(FIRST_NAME, LAST_NAME, SUPERHERO_NAME);
		SuperheroDTO result = superheroService.createSuperhero(request);
		assertEquals(FIRST_NAME, result.getFirstName());
		assertEquals(LAST_NAME, result.getLastName());
		assertEquals(SUPERHERO_NAME, result.getSuperheroName());
		Mockito.verify(superheroRepository).save(superheroCaptor.capture());
		Superhero savedSuperhero = superheroCaptor.getValue();
		assertEquals(FIRST_NAME, savedSuperhero.getFirstName());
		assertEquals(LAST_NAME, savedSuperhero.getLastName());
		assertEquals(SUPERHERO_NAME, savedSuperhero.getSuperheroName());
	}

	@Test
	public void findAllSuperheroes() {
		Superhero hero1 = SuperheroBuilder.aSuperhero().withSuperheroName("Batman").build();
		Superhero hero2 = SuperheroBuilder.aSuperhero().withSuperheroName("Superman").build();
		when(superheroRepository.findAll()).thenReturn(Arrays.asList(hero1, hero2));
		List<SuperheroDTO> allSuperheroes = superheroService.findAllSuperheroes();
		assertThat(allSuperheroes.stream().map(SuperheroDTO::getSuperheroName).collect(Collectors.toList()))
				.hasSize(2)
				.containsExactly("Batman", "Superman");
	}

}
