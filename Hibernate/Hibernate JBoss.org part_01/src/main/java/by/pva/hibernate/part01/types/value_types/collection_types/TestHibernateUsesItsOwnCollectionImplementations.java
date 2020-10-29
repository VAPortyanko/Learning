package by.pva.hibernate.part01.types.value_types.collection_types;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

public class TestHibernateUsesItsOwnCollectionImplementations {
	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query1 = entityManager.createQuery("delete from Person13");
		query1.executeUpdate();
		
		Person person = new Person();
		person.setId(1L);
		List<String> phones = new ArrayList<>();
		phones.add("+375(29)345-34-34");
		phones.add("+375(29)344-32-32");
		person.setPhones(phones);
		
		entityManager.persist(person);
		entityManager.flush();
		entityManager.clear();
		
		/* Next two line will be cause of the throwing
		 * java.lang.ClassCastException: org.hibernate.collection.internal.PersistentBag cannot be cast to java.util.ArrayList
		 * 
		 * Hibernate uses its own collection implementations!
		 */
		// Person person2 = entityManager.find( Person.class, 1L );
		// ArrayList<String> phones2 = (ArrayList<String>) person2.getPhones(); 
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
	}
}

@Entity(name = "Person13")
@Table(name = "persons13")
class Person {

	@Id
	private Long id;
	@ElementCollection
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(foreignKey =  @ForeignKey(name = "rty"), name = "asdf")
	private List<String> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
}