package by.pva.hibernate.part01.types.entity_types.associations.many_to_one.JoinFormula;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.JoinFormula;

public class TestManyToOne_JoinFormula {
	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Query query1 = entityManager.createQuery("delete from User");
		Query query2 = entityManager.createQuery("delete from Country3");
		query1.executeUpdate();
		query2.executeUpdate();
		
//		Country US = new Country();
//		US.setId(1);
//		US.setName("United States");

		Country Romania = new Country();
		Romania.setId(40);
		Romania.setName("Romania");

//		entityManager.persist(US);
		entityManager.persist(Romania);

//		User user1 = new User();
//		user1.setId(1L);
//		user1.setFirstName("John");
//		user1.setLastName("Doe");
//		user1.setPhoneNumber("+1-234-5678");
		
//		entityManager.persist(user1);

		User user2 = new User();
		user2.setId(2L);
		user2.setFirstName("Vlad");
		user2.setLastName("Mihalcea");
		user2.setPhoneNumber("+40-123-4567");
		
		entityManager.persist(user2);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		//User john = entityManager.find(User.class, 1L);
		//System.out.println(john.getCountry());

		User vlad = entityManager.find(User.class, 2L);
		System.out.println(vlad.getCountry().getName());
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@Entity(name = "User")
@Table(name = "users")
class User {

	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;

	@ManyToOne
	//@JoinFormula("REGEXP_REPLACE(phoneNumber, '\\+(\\d+)-.*', '\\1')::int")
	@JoinFormula("40")
	private Country country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}

@Entity(name = "Country3")
@Table(name = "countries3")
class Country {

	@Id
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

// CREATE TABLE countries (
//     id int4 NOT NULL,
//     name VARCHAR(255),
//     PRIMARY KEY ( id )
// )
//
// CREATE TABLE users (
//     id int8 NOT NULL,
//     firstName VARCHAR(255),
//     lastName VARCHAR(255),
//     phoneNumber VARCHAR(255),
//     PRIMARY KEY ( id )
// )
