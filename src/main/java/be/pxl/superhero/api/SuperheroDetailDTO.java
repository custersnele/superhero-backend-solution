package be.pxl.superhero.api;

import be.pxl.superhero.domain.Superhero;

import java.util.List;
import java.util.stream.Collectors;

public class SuperheroDetailDTO extends SuperheroDTO{

	private List<MissionDTO> missions;

	public SuperheroDetailDTO(Superhero superhero) {
		super(superhero);
		missions = superhero.getMissions().stream().map(MissionDTO::new).collect(Collectors.toList());
	}

	public SuperheroDetailDTO() {
	}

	public void setMissions(List<MissionDTO> missions) {
		this.missions = missions;
	}

	public List<MissionDTO> getMissions() {
		return missions;
	}
}
