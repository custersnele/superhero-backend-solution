package be.pxl.superhero.api;

public class HeroMissionRequest {
	private final Long missionId;
	private final Long superheroId;

	public HeroMissionRequest(Long missionId, Long superheroId) {
		this.missionId = missionId;
		this.superheroId = superheroId;
	}

	public Long getMissionId() {
		return missionId;
	}

	public Long getSuperheroId() {
		return superheroId;
	}
}
