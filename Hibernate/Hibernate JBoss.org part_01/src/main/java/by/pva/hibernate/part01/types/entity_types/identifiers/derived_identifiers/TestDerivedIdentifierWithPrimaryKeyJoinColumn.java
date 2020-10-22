package by.pva.hibernate.part01.types.entity_types.identifiers.derived_identifiers;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.NaturalId;

public class TestDerivedIdentifierWithPrimaryKeyJoinColumn {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		Person6 person = new Person6("ABC-123");
		person.setId(1L);

		Person6Details personDetails = new Person6Details();
		personDetails.setNickName("John Doe");
		personDetails.setPerson(person);

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(person);
		entityManager.persist(personDetails);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}

@Entity(name = "Person6")
class Person6 {

	@Id
	private Long id;

	@NaturalId
	private String registrationNumber;

	public Person6() {
	}

	public Person6(String registrationNumber) {
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

@Entity(name = "Person6Details")
class Person6Details {

	@Id
	private Long id;
	private String nickName;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Person6 person;

	public void setPerson(Person6 person) {
		this.person = person;
		this.id = person.getId(); // !!!
	}

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

	public Person6 getPerson() {
		return person;
	}

}
