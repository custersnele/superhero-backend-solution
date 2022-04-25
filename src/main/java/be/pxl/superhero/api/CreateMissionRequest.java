package be.pxl.superhero.api;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class CreateMissionRequest {

	@NotBlank(message = "missionName must not be blank")
	private final String missionName;

	@JsonCreator
	public CreateMissionRequest(String missionName) {
		this.missionName = missionName;
	}

	public String getMissionName() {
		return missionName;
	}
}
