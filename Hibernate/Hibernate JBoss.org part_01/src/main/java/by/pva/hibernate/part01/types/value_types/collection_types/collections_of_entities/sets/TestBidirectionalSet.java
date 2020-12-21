package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.sets;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

public class TestBidirectionalSet extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from Person24");
			Query query2 = entityManager.createQuery("delete from Phone17");
			query.executeUpdate();
			query2.executeUpdate();

			Person24 person = new Person24();
			person.getPhones().add(new Phone17("landline", "028-234-9876"));
			person.getPhones().add(new Phone17("mobile", "072-122-9876"));
			entityManager.persist(person);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Person24")
@Table(name = "Persons24")
class Person24 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private Set<Phone17> phones = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Phone17> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone17> phones) {
		this.phones = phones;
	}

	public void addPhone(Phone17 phone) {
		phones.add(phone);
		phone.setPerson(this);
	}

	public void removePhone(Phone17 phone) {
		phones.remove(phone);
		phone.setPerson(null);
	}
}

@Entity(name = "Phone17")
@Table(name = "Phones17")
class Phone17 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	@Column(name = "`number`", unique = true)
	@NaturalId
	private String number;
	@ManyToOne
	private Person24 person;

	public Phone17(String type, String number) {
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

	public Person24 getPerson() {
		return person;
	}

	public void setPerson(Person24 person) {
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
		Phone17 phone = (Phone17) o;
		return Objects.equals(number, phone.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
