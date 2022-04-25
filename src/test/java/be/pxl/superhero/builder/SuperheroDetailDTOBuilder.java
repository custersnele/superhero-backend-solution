package be.pxl.superhero.builder;

import be.pxl.superhero.api.SuperheroDetailDTO;

import java.util.Collections;

public final class SuperheroDetailDTOBuilder {
	public static final Long ID = 17L;
	public static final String FIRSTNAME = "Clark";
	public static final String LASTNAME = "Kent";
	public static final String SUPERHERO_NAME = "Superman";
	private Long id = ID;
	private String firstName = FIRSTNAME;
	private String lastName = LASTNAME;
	private String superheroName = SUPERHERO_NAME;

	private SuperheroDetailDTOBuilder() {}

	public static SuperheroDetailDTOBuilder aSuperheroDetailDTO() {return new SuperheroDetailDTOBuilder();}

	public SuperheroDetailDTOBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public SuperheroDetailDTOBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public SuperheroDetailDTOBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public SuperheroDetailDTOBuilder withSuperheroName(String superheroName) {
		this.superheroName = superheroName;
		return this;
	}

	public SuperheroDetailDTO build() {
		SuperheroDetailDTO superhero = new SuperheroDetailDTO();
		superhero.setId(id);
		superhero.setSuperheroName(superheroName);
		superhero.setFirstName(firstName);
		superhero.setLastName(lastName);
		superhero.setMissions(Collections.emptyList());
		return superhero;
	}
}
