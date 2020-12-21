package by.pva.hibernate.part01.types.entity_types.identifiers.derived_identifiers;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NaturalId;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestDerivedIdentifierWithMapsId extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person person = new Person("ABC-1234");
			person.setId(5L);

			PersonDetails personDetails = new PersonDetails();
			personDetails.setNickName("John Doe");
			personDetails.setPerson(person);

			entityManager.persist(person);
			entityManager.persist(personDetails);
		});

		entityManagerFactory.close();
	}
}

@Entity(name = "Persons5")
class Person {

	@Id
	private Long id;
	@NaturalId
	private String registrationNumber;

	public Person() {
	}

	public Person(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

}

@Entity(name = "Person5Details")
class PersonDetails {

	@Id
	private Long id;
	private String nickName;

	@OneToOne
	@MapsId
	private Person person;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
