package be.pxl.superhero.api;

import be.pxl.superhero.domain.Mission;

public class MissionDTO {

	private Long id;
	private String missionName;
	private boolean completed;
	private boolean deleted;

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

	public void setId(Long id) {
		this.id = id;
	}

	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
