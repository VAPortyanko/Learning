package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.ordered_lists;

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
import javax.persistence.OrderColumn;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.ListIndexBase;
import org.hibernate.annotations.NaturalId;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestBidirectionalOrderColumnWithListIndexBase extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from Phone15");
			Query query2 = entityManager.createQuery("delete from Person21");
			query.executeUpdate();
			query2.executeUpdate();

			Person21 person = new Person21();
			person.setId(1L);
			person.addPhone(new Phone15("landline", "128-234-9876"));
			person.addPhone(new Phone15("mobile", "372-122-9876"));
			person.addPhone(new Phone15("mobile", "073-122-9876"));
			entityManager.persist(person);
			entityManager.flush();
			entityManager.clear();

			Person21 person2 = entityManager.find(Person21.class, 1L);
			person2.getPhones().forEach(System.out::println);

		});

		entityManagerFactory.close();

	}

}

@Entity(name = "Person21")
@Table(name = "Persons21")
class Person21 {

	@Id
	private Long id;
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderColumn(name = "order_id")
	// You can customize the ordinal of the underlying ordered list by using the
	// @ListIndexBase annotation.
	// When inserting Phone records, Hibernate is going to start the List index from
	// 100 this time.
	@ListIndexBase(100)
	private List<Phone15> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Phone15> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone15> phones) {
		this.phones = phones;
	}

	public void addPhone(Phone15 phone) {
		phones.add(phone);
		phone.setPerson(this);
	}

	public void removePhone(Phone15 phone) {
		phones.remove(phone);
		phone.setPerson(null);
	}
}

@Entity(name = "Phone15")
@Table(name = "Phones15")
class Phone15 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	@Column(name = "`number`", unique = true)
	@NaturalId
	private String number;
	@ManyToOne
	private Person21 person;

	public Phone15() {
	}

	public Phone15(String type, String number) {
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

	public Person21 getPerson() {
		return person;
	}

	public void setPerson(Person21 person) {
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
		Phone15 phone = (Phone15) o;
		return Objects.equals(number, phone.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public String toString() {
		return this.type + ": " + this.number;
	}
}