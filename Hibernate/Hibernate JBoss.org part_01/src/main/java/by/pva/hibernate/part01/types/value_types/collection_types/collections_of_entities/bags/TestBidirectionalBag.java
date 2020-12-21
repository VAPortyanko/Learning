package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.bags;

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
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestBidirectionalBag extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from Phone11");
			Query query2 = entityManager.createQuery("delete from Person17");
			query.executeUpdate();
			query2.executeUpdate();

			Person17 person = new Person17();
			person.addPhone(new Phone11("landline", "028-234-9876"));
			person.addPhone(new Phone11("mobile", "072-122-9876"));
			person.addPhone(new Phone11("mobile", "073-122-9876"));
			entityManager.persist(person);
			entityManager.flush();
			person.removePhone(person.getPhones().get(0));

		});

		entityManagerFactory.close();

	}

}

@Entity(name = "Person17")
@Table(name = "Persons17")
class Person17 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Phone11> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public List<Phone11> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone11> phones) {
		this.phones = phones;
	}

	public void addPhone(Phone11 phone) {
		phones.add(phone);
		phone.setPerson(this);
	}

	public void removePhone(Phone11 phone) {
		phones.remove(phone);
		phone.setPerson(null);
	}
}

@Entity(name = "Phone11")
@Table(name = "Phones11")
class Phone11 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	@Column(name = "`number`", unique = true)
	@NaturalId
	private String number;
	@ManyToOne
	private Person17 person;

	public Phone11(String type, String number) {
		this.type = type;
		this.number = number;
	}

	public Long getId() {
		return id;
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

	public Person17 getPerson() {
		return person;
	}

	public void setPerson(Person17 person) {
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
		Phone11 phone = (Phone11) o;
		return Objects.equals(number, phone.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}

/*
 * CREATE TABLE Person ( id BIGINT NOT NULL, PRIMARY KEY (id) )
 * 
 * CREATE TABLE Phone ( id BIGINT NOT NULL, number VARCHAR(255), type
 * VARCHAR(255), person_id BIGINT, PRIMARY KEY (id) )
 * 
 * ALTER TABLE Phone ADD CONSTRAINT UK_l329ab0g4c1t78onljnxmbnp6 UNIQUE (number)
 * 
 * ALTER TABLE Phone ADD CONSTRAINT FKmw13yfsjypiiq0i1osdkaeqpg FOREIGN KEY
 * (person_id) REFERENCES Person
 */