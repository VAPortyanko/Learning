package by.pva.hibernate.part01.types.value_types.basic_types.mapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestEnumAsCustomTypeMapping extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person2 person2 = new Person2();
			person2.setGender(Gender.MALE);
			person2.setName("Kiril");

			entityManager.persist(person2);
		});

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
