package be.pxl.superhero.api;

import be.pxl.superhero.domain.Superhero;

public class SuperheroDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String superheroName;

	public SuperheroDTO(Superhero superhero) {
		this.id = superhero.getId();
		this.firstName = superhero.getFirstName();
		this.lastName = superhero.getLastName();
		this.superheroName = superhero.getSuperheroName();
	}

	public SuperheroDTO() {
	}

	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setSuperheroName(String superheroName) {
		this.superheroName = superheroName;
	}
}
