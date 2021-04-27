package by.pva.hibernate.part01.types.entity_types.associations.one_to_many;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestOneToManyBidirectional extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person8 person = new Person8();
			Phone4 phone1 = new Phone4("123-456-7890");
			Phone4 phone2 = new Phone4("321-654-0984");

			person.addPhone(phone1);
			person.addPhone(phone2);
			entityManager.persist(person);
			entityManager.flush();

			person.removePhone(phone1);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Person")
@Table(name = "persons8")
class Person8 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "person",  // That tells Hibernate "Go look over on the bean property named 'person' on the thing I have a collection of to find the configuration." 
			   cascade = CascadeType.ALL,
			   orphanRemoval = true)
	private List<Phone4> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Phone4> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone4> phones) {
		this.phones = phones;
	}

	public void addPhone(Phone4 phone) {
		phones.add(phone);
		phone.setPerson(this);
	}

	public void removePhone(Phone4 phone) {
		phones.remove(phone);
		phone.setPerson(null);
	}
}

@Entity(name = "Phone4")
@Table(name = "phones4")
class Phone4 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NaturalId
	@Column(name = "`number`", unique = true)
	private String number;
	@ManyToOne
	private Person8 person;

	public Phone4(String number) {
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

	public Person8 getPerson() {
		return person;
	}

	public void setPerson(Person8 person) {
		this.person = person;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Phone4 phone = (Phone4) o;
		return Objects.equals(number, phone.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}

//This is the equivalent of this
//
// CREATE TABLE Person (
//     id BIGINT NOT NULL ,
//     PRIMARY KEY ( id )
// )
//
// CREATE TABLE Phone (
//     id BIGINT NOT NULL ,
//     number VARCHAR(255) ,
//     person_id BIGINT ,
//     PRIMARY KEY ( id )
// )
//
// ALTER TABLE Phone
// ADD CONSTRAINT UK_l329ab0g4c1t78onljnxmbnp6
// UNIQUE (number)
//
// ALTER TABLE Phone
// ADD CONSTRAINT FKmw13yfsjypiiq0i1osdkaeqpg
// FOREIGN KEY (person_id) REFERENCES Person