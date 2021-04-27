package by.pva.hibernate.part01.types.entity_types.associations.many_to_many;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestManyToManyBidirectional extends BaseTest {
	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person10 person1 = new Person10("ABC-12431");
			Person10 person2 = new Person10("DEF-45611");

			Address2 address1 = new Address2("12th Avenue", "12A", "4005A");
			Address2 address2 = new Address2("18th Avenue", "18B", "4007B");
			Address2 address3 = new Address2("19th Avenue", "19B", "4009B");

			person1.addAddress(address1);
			person1.addAddress(address2);
			person1.addAddress(address3);

			person2.addAddress(address1);

			entityManager.persist(person1);
			entityManager.persist(person2);

			// If a bidirectional @OneToMany association performs better when removing or changing
			// the order of child elements, the @ManyToMany relationship cannot benefit from
			// such an optimization because the foreign key side is not in control. To overcome
			// this limitation, the link table must be directly exposed and the @ManyToMany association
			// split into two bidirectional @OneToMany relationships.
			// 
			// See: by.pva.hibernate.part01.types.entity_types.associations.many_to_many.TestManyToManyBiderectionalWithLinkEntity;
			entityManager.flush();

			person1.removeAddress(address1); // !!! Reinserting records in PersonAdress table after deleting one.

		});

		entityManagerFactory.close();
	}
}

@Entity(name = "Person10")
@Table(name = "Persons10")
class Person10 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NaturalId
	private String registrationNumber;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Address2> addresses = new ArrayList<>();

	public Person10(String registrationNumber) {
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

	public List<Address2> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address2> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address2 address) {
		addresses.add(address);
		address.getOwners().add(this);
	}

	public void removeAddress(Address2 address) {
		addresses.remove(address);
		address.getOwners().remove(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Person10 person = (Person10) o;
		return Objects.equals(registrationNumber, person.registrationNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(registrationNumber);
	}
}

@Entity(name = "Address2")
@Table(name = "Adresses2")
class Address2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street;
	@Column(name = "`number`")
	private String number;
	private String postalCode;
	@ManyToMany(mappedBy = "addresses")
	private List<Person10> owners = new ArrayList<>();

	public Address2(String street, String number, String postalCode) {
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

	public List<Person10> getOwners() {
		return owners;
	}

	public void setOwners(List<Person10> owners) {
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
		Address2 address = (Address2) o;
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
//     registrationNumber VARCHAR(255) ,
//     PRIMARY KEY ( id )
// )
//
// CREATE TABLE Person_Address (
//     owners_id BIGINT NOT NULL ,
//     addresses_id BIGINT NOT NULL
// )
//
// ALTER TABLE Person
// ADD CONSTRAINT UK_23enodonj49jm8uwec4i7y37f
// UNIQUE (registrationNumber)
//
// ALTER TABLE Person_Address
// ADD CONSTRAINT FKm7j0bnabh2yr0pe99il1d066u
// FOREIGN KEY (addresses_id) REFERENCES Address
//
// ALTER TABLE Person_Address
// ADD CONSTRAINT FKbn86l24gmxdv2vmekayqcsgup
// FOREIGN KEY (owners_id) REFERENCES Person