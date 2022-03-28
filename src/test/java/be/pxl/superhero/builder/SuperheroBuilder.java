package be.pxl.superhero.builder;

import be.pxl.superhero.domain.Superhero;

public final class SuperheroBuilder {
	public static final Long ID = 17L;
	public static final String FIRSTNAME = "Clark";
	public static final String LASTNAME = "Kent";
	public static final String SUPERHERO_NAME = "Superman";
	private Long id = ID;
	private String firstName = FIRSTNAME;
	private String lastName = LASTNAME;
	private String superheroName = SUPERHERO_NAME;

	private SuperheroBuilder() {}

	public static SuperheroBuilder aSuperhero() {return new SuperheroBuilder();}

	public SuperheroBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public SuperheroBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public SuperheroBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public SuperheroBuilder withSuperheroName(String superheroName) {
		this.superheroName = superheroName;
		return this;
	}

	public Superhero build() {
		Superhero superhero = new Superhero(firstName, lastName, superheroName);
		superhero.setId(id);
		return superhero;
	}
}
