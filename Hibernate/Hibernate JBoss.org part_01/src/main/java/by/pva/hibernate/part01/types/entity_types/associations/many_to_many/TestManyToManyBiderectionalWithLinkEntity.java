package by.pva.hibernate.part01.types.entity_types.associations.many_to_many;

import java.io.Serializable;
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

public class TestManyToManyBiderectionalWithLinkEntity extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {
			
			Person11 person1 = new Person11("ABC-124");
			Person11 person2 = new Person11("DEF-457");

			Address3 address1 = new Address3("12th Avenue", "12A", "4005A");
			Address3 address2 = new Address3("18th Avenue", "18B", "4007B");
			Address3 address3 = new Address3("19th Avenue", "19B", "4008B");

			entityManager.persist(person1);
			entityManager.persist(person2);

			entityManager.persist(address1);
			entityManager.persist(address2);
			entityManager.persist(address3);

			person1.addAddress(address1);
			person1.addAddress(address2);
			person1.addAddress(address3);

			person2.addAddress(address1);

			entityManager.flush();

			person1.removeAddress(address1);

		});

		entityManagerFactory.close();
	}
}

@Entity(name = "Person11")
@Table(name = "Persons11")
class Person11 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NaturalId
	private String registrationNumber;
	@OneToMany(mappedBy = "person", 
			   cascade = CascadeType.ALL,
			   orphanRemoval = true)
	private List<Person11Address3> addresses = new ArrayList<>();

	public Person11(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public void addAddress(Address3 address) {
		Person11Address3 personAddress = new Person11Address3(this, address);
		addresses.add(personAddress);
		address.getOwners().add(personAddress);
	}

	public void removeAddress(Address3 address) {
		Person11Address3 personAddress = new Person11Address3(this, address);
		address.getOwners().remove(personAddress);
		addresses.remove(personAddress);
		personAddress.setPerson(null);
		personAddress.setAddress(null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Person11 person = (Person11) o;
		return Objects.equals(registrationNumber, person.registrationNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(registrationNumber);
	}
}

@Entity(name = "Person11Address3")
@Table(name = "Person11_Address3")
class Person11Address3 implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	private Person11 person;
	@Id
	@ManyToOne
	private Address3 address;

	public Person11Address3(Person11 person, Address3 address) {
		this.person = person;
		this.address = address;
	}

	public Person11 getPerson() {
		return person;
	}

	public void setPerson(Person11 person) {
		this.person = person;
	}

	public Address3 getAddress() {
		return address;
	}

	public void setAddress(Address3 address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Person11Address3 that = (Person11Address3) o;
		return Objects.equals(person, that.person) && Objects.equals(address, that.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(person, address);
	}
}

@Entity(name = "Address3")
@Table(name = "Adresses3")
class Address3 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street;
	@Column(name = "`number`")
	private String number;
	private String postalCode;
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Person11Address3> owners = new ArrayList<>();

	public Address3(String street, String number, String postalCode) {

		this.street = street;
		this.number = number;
		this.postalCode = postalCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<Person11Address3> getOwners() {
		return owners;
	}

	public void setOwners(List<Person11Address3> owners) {
		this.owners = owners;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Address3 address = (Address3) o;
		return Objects.equals(street, address.street) && Objects.equals(number, address.number)
				&& Objects.equals(postalCode, address.postalCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(street, number, postalCode);
	}
}

// CREATE TABLE Address (
//     id BIGINT NOT NULL ,
//     number VARCHAR(255) ,
//     postalCode VARCHAR(255) ,
//     street VARCHAR(255) ,
//     PRIMARY KEY ( id )
// ) 
//
// CREATE TABLE Person (
//     id BIGINT NOT NULL ,
//    registrationNumber VARCHAR(255) ,
//     PRIMARY KEY ( id )
// )
//
// CREATE TABLE PersonAddress (
//     person_id BIGINT NOT NULL ,
//     address_id BIGINT NOT NULL ,
//     PRIMARY KEY ( person_id, address_id )
// )
//
// ALTER TABLE Person
// ADD CONSTRAINT UK_23enodonj49jm8uwec4i7y37f
// UNIQUE (registrationNumber)
//
// ALTER TABLE PersonAddress
// ADD CONSTRAINT FK8b3lru5fyej1aarjflamwghqq
// FOREIGN KEY (person_id) REFERENCES Person
//
// ALTER TABLE PersonAddress
// ADD CONSTRAINT FK7p69mgialumhegyl4byrh65jk
// FOREIGN KEY (address_id) REFERENCES Address
