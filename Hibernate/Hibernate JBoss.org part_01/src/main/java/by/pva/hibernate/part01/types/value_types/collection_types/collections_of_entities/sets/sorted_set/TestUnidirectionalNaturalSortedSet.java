package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.sets.sorted_set;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SortNatural;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestUnidirectionalNaturalSortedSet extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from Person25");
			Query query2 = entityManager.createQuery("delete from Phone18");
			query.executeUpdate();
			query2.executeUpdate();

			Person person = new Person();
			person.getPhones().add(new Phone("landline", "228-234-9876"));
			person.getPhones().add(new Phone("mobile", "672-122-9876"));
			person.getPhones().add(new Phone("mobile", "772-122-9876"));
			person.getPhones().add(new Phone("mobile", "472-122-9876"));
			person.getPhones().add(new Phone("landline", "102-234-9876"));
			person.getPhones().add(new Phone("landline", "928-234-9876"));

			entityManager.persist(person);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Person25")
@Table(name = "Persons25")
class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL)
	@SortNatural
	private SortedSet<Phone> phones = new TreeSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SortedSet<Phone> getPhones() {
		return phones;
	}

	public void setPhones(SortedSet<Phone> phones) {
		this.phones = phones;
	}
}

@Entity(name = "Phone18")
@Table(name = "Phones18")
class Phone implements Comparable<Phone> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	@NaturalId
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

	@Override
	public int compareTo(Phone o) {
		return number.compareTo(o.getNumber());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Phone phone = (Phone) o;
		return Objects.equals(number, phone.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public String toString() {
		return number + " " + type;
	}
}
