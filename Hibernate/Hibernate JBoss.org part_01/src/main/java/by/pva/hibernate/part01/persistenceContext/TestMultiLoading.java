package by.pva.hibernate.part01.persistenceContext;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.Session;

public class TestMultiLoading {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("delete from Person35");
		query.executeUpdate();

		Person person1 = new Person(1L, "Person1");
		Person person2 = new Person(2L, "Person2");
		Person person3 = new Person(3L, "Person3");
		
		entityManager.persist(person1);
		entityManager.persist(person2);
		entityManager.persist(person3);

		Session session = entityManager.unwrap(Session.class);
		
		List<Person> persons = session.byMultipleIds(Person.class)
                                      .multiLoad(3L, 1L, 2L);

		System.out.println("persons (" + persons.size() + "):");
		persons.stream().forEach(System.out::println);
		
		// An additional select query will be executed if we uncomment the code below or set the enableSessionCheck to false.
		//session.flush();
		//session.detach(person3);
		
		// No select queries to the database will be executed because enableSessionCheck is set to "true" and all required 
		// data present in the persistence context.
		List<Person> samePersons = session.byMultipleIds(Person.class)
				                          .enableSessionCheck(true)
				                          .multiLoad(3L, 1L, 2L);
		
		System.out.println("samePersons (" + samePersons.size() + "):");
		samePersons.stream().forEach(System.out::println);

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@Entity(name = "Person35")
@Table(name = "Persons35")
class Person {

	public Person() {
	}
	
	public Person(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Id
	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

}
