package by.pva.hibernate.part01.types.entity_types.associations.one_to_many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestOneToManyUnidirectional extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person7 person = new Person7();
			Phone3 phone1 = new Phone3("123-456-7890");
			Phone3 phone2 = new Phone3("421-654-4980");
			Phone3 phone3 = new Phone3("922-155-7981");
			Phone3 phone4 = new Phone3("123-956-2982");
			person.getPhones().add(phone1);
			person.getPhones().add(phone2);
			person.getPhones().add(phone3);
			person.getPhones().add(phone4);

			entityManager.persist(person);
			entityManager.flush();

			// The unidirectional associations are not very efficient when it comes to
			// removing child entities.
			// In the example above, upon flushing the persistence context, Hibernate
			// deletes all database rows
			// from the link table (e.g. Person_Phone) that are associated with the parent
			// Person entity and
			// reinserts the ones that are still found in the @OneToMany collection.
			person.getPhones().remove(phone1);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Person7")
@Table(name = "persons7")
class Person7 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// When persisting the Person entity, the cascade will propagate the persist operation to the underlying Phone children as well.
	// Upon removing a Phone from the phones collection, the association row is deleted from the link table, 
	// and the orphanRemoval attribute will trigger a Phone removal as well.
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

@Entity(name = "Phone3")
@Table(name = "Phones3")
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