package by.pva.hibernate.part01.types.value_types.collection_types.collectionsOfEntities.orderedLists;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

public class TestBidirecionalOrderBy {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("delete from Phone14");
		Query query2 = entityManager.createQuery("delete from Person20");
		query.executeUpdate();
		query2.executeUpdate();

		Person20 person = new Person20();
		person.setId(1L);
		person.addPhone(new Phone14("landline", "128-234-9876"));
		person.addPhone(new Phone14("mobile", "372-122-9876"));
		person.addPhone(new Phone14("mobile", "073-122-9876"));
		entityManager.persist(person);
		entityManager.flush();
		entityManager.clear();
		
		Person20 person2 = entityManager.find(Person20.class, 1L); 
		person2.getPhones().forEach(System.out::println);

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}

}

@Entity(name = "Person20")
@Table(name = "Persons20")
class Person20 {

	@Id
	private Long id;
	@OneToMany(mappedBy = "person", 
			   cascade = CascadeType.ALL,
			   orphanRemoval = true)
	@OrderBy("number")
	private List<Phone14> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Phone14> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone14> phones) {
		this.phones = phones;
	}

	public void addPhone(Phone14 phone) {
		phones.add(phone);
		phone.setPerson(this);
	}

	public void removePhone(Phone14 phone) {
		phones.remove(phone);
		phone.setPerson(null);
	}
}

@Entity(name = "Phone14")
@Table(name = "Phones14")
class Phone14 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	@Column(name = "`number`", unique = true)
	@NaturalId
	private String number;
	@ManyToOne
	private Person20 person;

	public Phone14() {
	}
	public Phone14(String type, String number) {
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

	public Person20 getPerson() {
		return person;
	}

	public void setPerson(Person20 person) {
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
		Phone14 phone = (Phone14) o;
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