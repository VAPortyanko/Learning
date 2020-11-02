package by.pva.hibernate.part01.types.value_types.collection_types.collectionsOfEntities.sets;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

public class TestUnidirectionalSet {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("delete from Person23");
		Query query2 = entityManager.createQuery("delete from Phone16");
		query.executeUpdate();
		query2.executeUpdate();
		
		Person person = new Person();
		person.getPhones().add(new Phone("landline", "028-234-9876"));
		person.getPhones().add(new Phone("mobile", "072-122-9876"));
		entityManager.persist(person);

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
	}
}

@Entity(name = "Person23")
@Table(name = "Persons23")
class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Phone> phones = new HashSet<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Phone> getPhones() {
		return phones;
	}
	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}
}

@Entity(name = "Phone16")
@Table(name = "Phones16")
class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	@NaturalId
	@Column(name = "`number`")
	private String number;

	public Phone() {
	}
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
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		Phone phone = (Phone) o;
		return Objects.equals(number, phone.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}