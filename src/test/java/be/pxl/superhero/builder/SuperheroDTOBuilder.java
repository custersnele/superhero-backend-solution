package be.pxl.superhero.builder;

import be.pxl.superhero.api.SuperheroDTO;

public final class SuperheroDTOBuilder {
	public static final Long SUPERHERO_ID = 15L;
	public static final String FIRSTNAME = "Clark";
	public static final String LASTNAME = "Kent";
	public static final String SUPERHERO_NAME = "Superman";

	private Long id = SUPERHERO_ID;
	private String firstName = FIRSTNAME;
	private String lastName = LASTNAME;
	private String superheroName = SUPERHERO_NAME;

	private SuperheroDTOBuilder() {}

	public static SuperheroDTOBuilder aSuperheroDTO() {return new SuperheroDTOBuilder();}

	public SuperheroDTOBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public SuperheroDTOBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public SuperheroDTOBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public SuperheroDTOBuilder withSuperheroName(String superheroName) {
		this.superheroName = superheroName;
		return this;
	}

	public SuperheroDTO build() {
		SuperheroDTO superheroDTO = new SuperheroDTO();
		superheroDTO.setId(id);
		superheroDTO.setFirstName(firstName);
		superheroDTO.setLastName(lastName);
		superheroDTO.setSuperheroName(superheroName);
		return superheroDTO;
	}
}
