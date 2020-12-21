package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.bags;

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

public class TestUnidirectionalBag extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person person = new Person();
			person.getPhones().add(new Phone("landline", "028-234-9876"));
			person.getPhones().add(new Phone("mobile", "072-122-9876"));
			entityManager.persist(person);

		});

		entityManagerFactory.close();

	}

}

@Entity(name = "Person16")
@Table(name = "Persons16")
class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// The cascading mechanism allows you to propagate an entity state transition
	// from a parent entity to its children.
	// Once the parent entity is persisted, the child entities are going to be
	// persisted as well.
	@OneToMany(cascade = CascadeType.ALL)
	private List<Phone> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

}

@Entity(name = "Phone10")
@Table(name = "Phines10")
class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	@Column(name = "`number`")
	private String number;

	public Phone(String type, String number) {
		this.type = type;
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}

/*
 * CREATE TABLE Person ( id BIGINT NOT NULL , PRIMARY KEY ( id ) )
 * 
 * CREATE TABLE Person_Phone ( Person_id BIGINT NOT NULL , phones_id BIGINT NOT
 * NULL )
 * 
 * CREATE TABLE Phone ( id BIGINT NOT NULL , number VARCHAR(255) , type
 * VARCHAR(255) , PRIMARY KEY ( id ) )
 * 
 * ALTER TABLE Person_Phone ADD CONSTRAINT UK_9uhc5itwc9h5gcng944pcaslf UNIQUE
 * (phones_id)
 * 
 * ALTER TABLE Person_Phone ADD CONSTRAINT FKr38us2n8g5p9rj0b494sd3391 FOREIGN
 * KEY (phones_id) REFERENCES Phone
 * 
 * ALTER TABLE Person_Phone ADD CONSTRAINT FK2ex4e4p7w1cj310kg2woisjl2 FOREIGN
 * KEY (Person_id) REFERENCES Person
 */
