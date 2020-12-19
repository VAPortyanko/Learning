package by.pva.hibernate.part01.types.entity_types.associations.not_found_association_mapping;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

public class TestNotFoundAssociationMapping {
	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("delete from City");
		Query query2 = entityManager.createQuery("delete from Person12");
		query.executeUpdate();
		query2.executeUpdate();
		
		City newYork = new City();
		newYork.setName("New York");
		entityManager.persist(newYork);

		Person person = new Person();
		person.setId(1L);
		person.setName("John Doe");
		person.setCityName("New York");
		person.setCity(newYork);
		entityManager.persist(person);

		Person person2 = entityManager.find(Person.class, 1L);
		System.out.println(person2);

		person.setCityName("Atlantis");
		entityManager.flush();
		entityManager.clear(); 
		
		Person person3 = entityManager.find(Person.class, 1L);
		System.out.println(person3);
		
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManagerFactory.close();

	}
}

@Entity(name = "Person12")
@Table(name = "Persons12")
class Person {

	@Id
	private Long id;
	private String name;
	private String cityName;
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "cityName",
	            referencedColumnName = "name",
	            insertable = false,
	            updatable = false)
	private City city;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "id = " + id + " name = " + name + " cityName = " + cityName + " city.class = " + city;
	}

}

@Entity(name = "City")
@Table(name = "Cities")
class City implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
