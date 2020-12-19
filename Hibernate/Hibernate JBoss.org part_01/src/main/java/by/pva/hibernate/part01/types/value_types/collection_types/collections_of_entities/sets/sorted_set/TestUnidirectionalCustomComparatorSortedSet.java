package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.sets.sorted_set;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

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
import org.hibernate.annotations.SortComparator;

public class TestUnidirectionalCustomComparatorSortedSet {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("delete from Person26");
		Query query2 = entityManager.createQuery("delete from Phone19");
		query.executeUpdate();
		query2.executeUpdate();
		
		Person26 person = new Person26();
		person.getPhones().add(new Phone19("landline", "228-234-9876"));
		person.getPhones().add(new Phone19("mobile"  , "672-122-9876"));
		person.getPhones().add(new Phone19("mobile"  , "772-122-9876"));
		person.getPhones().add(new Phone19("mobile"  , "472-122-9876"));
		person.getPhones().add(new Phone19("landline", "102-234-9876"));
		person.getPhones().add(new Phone19("landline", "928-234-9876"));
		
		entityManager.persist(person);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}

@Entity(name = "Person26")
@Table(name = "Persons26")
class Person26 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL)
	@SortComparator(ReverseComparator.class)
	private SortedSet<Phone19> phones = new TreeSet<>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SortedSet<Phone19> getPhones() {
		return phones;
	}
	public void setPhones(SortedSet<Phone19> phones) {
		this.phones = phones;
	}
}

@Entity(name = "Phone19")
@Table(name = "Phones19")
class Phone19 implements Comparable<Phone19> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	@NaturalId
	@Column(name = "`number`")
	private String number;

	public Phone19(String type, String number) {
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
	public int compareTo(Phone19 o) {
		return number.compareTo( o.getNumber() );
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		Phone19 phone = (Phone19) o;
		return Objects.equals( number, phone.number );
	}

	@Override
	public int hashCode() {
		return Objects.hash( number );
	}
}