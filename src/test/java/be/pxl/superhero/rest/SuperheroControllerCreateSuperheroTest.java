package be.pxl.superhero.rest;

import be.pxl.superhero.api.SuperheroRequest;
import be.pxl.superhero.builder.SuperheroRequestBuilder;
import be.pxl.superhero.service.SuperheroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static be.pxl.superhero.builder.SuperheroRequestBuilder.FIRSTNAME;
import static be.pxl.superhero.builder.SuperheroRequestBuilder.LASTNAME;
import static be.pxl.superhero.builder.SuperheroRequestBuilder.SUPERHERO_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SuperheroController.class)
public class SuperheroControllerCreateSuperheroTest {
	@MockBean
	private SuperheroService superheroService;

	@Autowired
	private MockMvc mockMvc;

	@Captor
	private ArgumentCaptor<SuperheroRequest> superheroRequestCaptor;

	@Test
	public void testLastNameIsRequired() throws Exception {
		SuperheroRequest superheroRequest = SuperheroRequestBuilder
				.aSuperheroRequest()
				.withLastName("")
				.build();
		mockMvc.perform(MockMvcRequestBuilders.post("/superheroes")
						.content(asJsonString(superheroRequest))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testLastNameWithOneCharacterIsNotValid() throws Exception {
		SuperheroRequest superheroRequest = SuperheroRequestBuilder
				.aSuperheroRequest()
				.withLastName("A")
				.build();
		mockMvc.perform(MockMvcRequestBuilders.post("/superheroes")
						.content(asJsonString(superheroRequest))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}


	@Test
	public void testSuperheroNameIsRequired() throws Exception {
		SuperheroRequest superheroRequest = SuperheroRequestBuilder
				.aSuperheroRequest()
				.withSuperheroName("")
				.build();
		mockMvc.perform(MockMvcRequestBuilders.post("/superheroes")
						.content(asJsonString(superheroRequest))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testSuperheroIsCreatedWhenAllConstraintsSatified() throws Exception {
		SuperheroRequest superheroRequest = SuperheroRequestBuilder.aSuperheroRequest().build();
		mockMvc.perform(MockMvcRequestBuilders.post("/superheroes")
						.content(asJsonString(superheroRequest))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		verify(superheroService).createSuperhero(superheroRequestCaptor.capture());
		SuperheroRequest createdSuperhero = superheroRequestCaptor.getValue();
		assertThat(createdSuperhero.getFirstName()).isEqualTo(FIRSTNAME);
		assertThat(createdSuperhero.getLastName()).isEqualTo(LASTNAME);
		assertThat(createdSuperhero.getSuperheroName()).isEqualTo(SUPERHERO_NAME);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
