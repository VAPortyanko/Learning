package by.pva.hibernate.part01.types.entity_types.associations.one_to_many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;

public class TestOneToManyUnidirectional {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Person7 person = new Person7();
		Phone3 phone1 = new Phone3("123-456-7890");
		Phone3 phone2 = new Phone3("321-654-0987");
		person.getPhones().add(phone1);
		person.getPhones().add(phone2);

		entityManager.persist(person);
		entityManager.flush();

		person.getPhones().remove(phone1);

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@Entity(name = "Persons7")
class Person7 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Phone3> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Phone3> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone3> phones) {
		this.phones = phones;
	}

}

@Entity(name = "Phones3")
class Phone3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "`number`")
	private String number;

	public Phone3(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}

// This is the equivalent of this
//
// CREATE TABLE Person (
//     id BIGINT NOT NULL ,
//     PRIMARY KEY ( id )
// )
//
// CREATE TABLE Person_Phone (
//     Person_id BIGINT NOT NULL ,
//     phones_id BIGINT NOT NULL
// )
//
// CREATE TABLE Phone (
//     id BIGINT NOT NULL ,
//     number VARCHAR(255) ,
//     PRIMARY KEY ( id )
// )
//
// ALTER TABLE Person_Phone
// ADD CONSTRAINT UK_9uhc5itwc9h5gcng944pcaslf
// UNIQUE (phones_id)
//
// ALTER TABLE Person_Phone
// ADD CONSTRAINT FKr38us2n8g5p9rj0b494sd3391
// FOREIGN KEY (phones_id) REFERENCES Phone
//
// ALTER TABLE Person_Phone
// ADD CONSTRAINT FK2ex4e4p7w1cj310kg2woisjl2
// FOREIGN KEY (Person_id) REFERENCES Person