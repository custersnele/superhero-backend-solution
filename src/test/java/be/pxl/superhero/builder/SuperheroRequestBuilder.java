package be.pxl.superhero.builder;

import be.pxl.superhero.api.SuperheroRequest;

public final class SuperheroRequestBuilder {
	public static final String FIRSTNAME = "Clark";
	public static final String LASTNAME = "Kent";
	public static final String SUPERHERO_NAME = "Superman";
	private String firstName = FIRSTNAME;
	private String lastName = LASTNAME;
	private String superheroName = SUPERHERO_NAME;

	private SuperheroRequestBuilder() {}

	public static SuperheroRequestBuilder aSuperheroRequest() {return new SuperheroRequestBuilder();}

	public SuperheroRequestBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public SuperheroRequestBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public SuperheroRequestBuilder withSuperheroName(String superheroName) {
		this.superheroName = superheroName;
		return this;
	}

	public SuperheroRequest build() {return new SuperheroRequest(firstName, lastName, superheroName);}
}
