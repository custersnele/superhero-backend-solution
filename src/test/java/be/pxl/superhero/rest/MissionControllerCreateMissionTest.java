package be.pxl.superhero.rest;

import be.pxl.superhero.api.CreateMissionRequest;
import be.pxl.superhero.builder.MissionDTOBuilder;
import be.pxl.superhero.service.MissionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = MissionController.class)
public class MissionControllerCreateMissionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MissionService missionService;

	@Test
	public void badRequestWhenNoMissionName() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/missions")
				.content("{\"missionName\":\" \"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
				//.andExpect(MockMvcResultMatchers.content().);
	}

	@Test
	public void newMissionIsCreated() throws Exception {
		when(missionService.createMission(Mockito.any(CreateMissionRequest.class)))
				.thenReturn(MissionDTOBuilder.aMissionDTO().withMissionName("My mission").build());

		mockMvc.perform(MockMvcRequestBuilders.post("/missions")
						.content("{\"missionName\":\"My mission\"}")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(MissionDTOBuilder.ID))
				.andExpect(MockMvcResultMatchers.jsonPath("$.missionName").value("My mission"));
	}


}
