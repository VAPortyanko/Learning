package by.pva.hibernate.part01.types.value_types.collection_types.collectionsOfValueTypes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;

public class TestCollectionOfValueTypes {
	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Person person = new Person();
		List<String> phones = new ArrayList<>();
		phones.add("+375(29)345-34-34");
		phones.add("+375(29)344-32-32");

		person.setPhones(phones);

		entityManager.persist(person);
		entityManager.flush();

		person.getPhones().clear();
		person.getPhones().add("123-456-7890");
		person.getPhones().add("456-000-1234");
		person.getPhones().add("320-750-7891");
		person.getPhones().add("653-102-9231");
		
		entityManager.flush();
		
		person.getPhones().remove(0); // Delete all records with the current person id and re-insert remaining ones.
		                              // One of the way to avoid this is using the @OrderColumn annotation.
		                              // 
		                              // The @OrderColumn column works best when removing from the tail of the collection, 
		                              // as it only requires a single delete statement. Removing from the head or 
		                              // the middle of the collection requires deleting the extra elements and updating
		                              // the remaining ones to preserve element order.

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@Entity(name = "Person14")
@Table(name = "persons14")
class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ElementCollection
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
