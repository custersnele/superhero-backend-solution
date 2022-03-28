package be.pxl.superhero.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SuperheroRequest {

	private final String firstName;
	@NotBlank(message = "lastName must not be blank")
	@Size(message="lastName must have at least 2 characters.", min=2)
	private final String lastName;
	@NotBlank(message = "Superhero name must not be blank")
	private final String superheroName;

	public SuperheroRequest(String firstName, String lastName, String superheroName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.superheroName = superheroName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSuperheroName() {
		return superheroName;
	}

}
