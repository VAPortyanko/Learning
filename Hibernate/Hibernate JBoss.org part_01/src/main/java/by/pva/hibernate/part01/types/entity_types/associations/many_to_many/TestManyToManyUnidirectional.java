package by.pva.hibernate.part01.types.entity_types.associations.many_to_many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestManyToManyUnidirectional extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person person1 = new Person();
			Person person2 = new Person();

			Address address1 = new Address("12th Avenue", "12A");
			Address address2 = new Address("18th Avenue", "18B");
			Address address3 = new Address("19th Avenue", "19B");
			Address address4 = new Address("20th Avenue", "20B");

			person1.getAddresses().add(address1);
			person1.getAddresses().add(address2);
			person1.getAddresses().add(address3);
			person1.getAddresses().add(address4);

			person2.getAddresses().add(address1);
			person2.getAddresses().add(address2);

			entityManager.persist(person1);
			entityManager.persist(person2);

			entityManager.flush();
			
			Long id = person1.getId();
			System.out.println(id);
			
			// When an entity is removed from the @ManyToMany collection, Hibernate simply
			// deletes the joining record in the link table. Unfortunately, this operation requires
			// removing all entries associated with a given parent and recreating the ones that are
			// listed in the current running persistent context.
			person1.getAddresses().remove(address1);
			
			entityManager.remove(person1); // It works if the person1 in the pesistent context (with any annotation (Ano1 or Ano2))
			                               // But if place the entityManager.clear() expression before this line with Ano1 we will get the Exception.
			
// This doesn't work with Ano1.
//			entityManager.flush();
//			entityManager.clear();
//			entityManager.remove(entityManager.find(Person.class, id));

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Person8")
@Table(name = "Persons8")
class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// @ManyToMany(cascade = CascadeType.ALL)                               // Ano1
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })       // Ano2
	private List<Address> addresses = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}

@Entity(name = "Address")
@Table(name = "Adresses")
class Address {

	public Address() {
	}
	
	public Address(String street, String number) {
		this.street = street;
		this.number = number;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street;
	@Column(name = "`number`")
	private String number;

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

}

// CREATE TABLE Address (
//     id BIGINT NOT NULL ,
//     number VARCHAR(255) ,
//     street VARCHAR(255) ,
//     PRIMARY KEY ( id )
// )
//
// CREATE TABLE Person (
//     id BIGINT NOT NULL ,
//     PRIMARY KEY ( id )
// )
//
// CREATE TABLE Person_Address (
//     Person_id BIGINT NOT NULL ,
//     addresses_id BIGINT NOT NULL
// )
//
// ALTER TABLE Person_Address
// ADD CONSTRAINT FKm7j0bnabh2yr0pe99il1d066u
// FOREIGN KEY (addresses_id) REFERENCES Address
//
// ALTER TABLE Person_Address
// ADD CONSTRAINT FKba7rc9qe2vh44u93u0p2auwti
// FOREIGN KEY (Person_id) REFERENCES Person
