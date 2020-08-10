package by.pva.hibernate.part01.types.value_types.basic_types.mapping;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

import org.hibernate.annotations.Type;

public class TestEnumAsCustomTypeMapping {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		Person2 person2 = new Person2();
		person2.setGender(Gender.MALE);
		person2.setName("Kiril");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(person2);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}

@Entity(name = "Persons2")
class Person2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Type(type = "by.pva.hibernate.part01.types.value_types.basic_types.mapping.GenderType")
	public Gender gender;

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}

