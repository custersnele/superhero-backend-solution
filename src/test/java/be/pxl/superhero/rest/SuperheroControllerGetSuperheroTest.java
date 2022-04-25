package be.pxl.superhero.rest;

import be.pxl.superhero.api.SuperheroDetailDTO;
import be.pxl.superhero.builder.SuperheroDetailDTOBuilder;
import be.pxl.superhero.exception.ResourceNotFoundException;
import be.pxl.superhero.service.SuperheroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static be.pxl.superhero.builder.SuperheroDTOBuilder.FIRSTNAME;
import static be.pxl.superhero.builder.SuperheroDTOBuilder.LASTNAME;
import static be.pxl.superhero.builder.SuperheroDTOBuilder.SUPERHERO_ID;
import static be.pxl.superhero.builder.SuperheroDTOBuilder.SUPERHERO_NAME;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SuperheroController.class)
public class SuperheroControllerGetSuperheroTest {

	@MockBean
	private SuperheroService superheroService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getSuperheroByIdReturnsExistingSuperhero() throws Exception {
		SuperheroDetailDTO superheroDetailDTO = SuperheroDetailDTOBuilder
				.aSuperheroDetailDTO()
				.withId(SUPERHERO_ID)
				.build();
		when(superheroService.findSuperheroById(SUPERHERO_ID))
				.thenReturn(superheroDetailDTO);
		mockMvc.perform(MockMvcRequestBuilders
						.get("/superheroes/{id}", SUPERHERO_ID)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(SUPERHERO_ID))
				.andExpect(jsonPath("$.firstName").value(FIRSTNAME))
				.andExpect(jsonPath("$.lastName").value(LASTNAME))
				.andExpect(jsonPath("$.superheroName").value(SUPERHERO_NAME));
	}

	@Test
	public void getSuperheroByIdReturnsNotFoundWhenResourceNotFoundExceptionIsThrown() throws Exception {
		doThrow(ResourceNotFoundException.class)
				.when(superheroService)
				.findSuperheroById(SUPERHERO_ID);
		mockMvc.perform(MockMvcRequestBuilders
						.get("/superheroes/{id}", SUPERHERO_ID)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
}
