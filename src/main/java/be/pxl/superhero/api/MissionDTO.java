package be.pxl.superhero.api;

import be.pxl.superhero.domain.Mission;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.util.List;

public class MissionDTO {

	private Long id;
	private String missionName;
	private boolean completed;
	private boolean deleted;
	private List<SuperheroDTO> superheroes;

	public MissionDTO(Mission mission) {
		this.id = mission.getId();
		this.missionName = mission.getName();
		this.completed = mission.isCompleted();
		this.deleted = mission.isDeleted();
	}

	public MissionDTO() {
	}

	public Long getId() {
		return id;
	}

	public String getMissionName() {
		return missionName;
	}

	public boolean isCompleted() {
		return completed;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public List<SuperheroDTO> getSuperheroes() {
		return superheroes;
	}
}
