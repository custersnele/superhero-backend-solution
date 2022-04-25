package be.pxl.superhero.api;

import be.pxl.superhero.domain.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionDetailDTO extends MissionDTO {

	private final List<SuperheroDTO> superheroes;

	public MissionDetailDTO(Mission mission) {
		super(mission);
		superheroes = mission.getSuperheroes().stream().map(SuperheroDTO::new).collect(Collectors.toList());
	}

	public List<SuperheroDTO> getSuperheroes() {
		return superheroes;
	}
}
