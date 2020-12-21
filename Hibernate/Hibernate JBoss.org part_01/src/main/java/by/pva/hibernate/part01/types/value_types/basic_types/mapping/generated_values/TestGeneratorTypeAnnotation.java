package by.pva.hibernate.part01.types.value_types.basic_types.mapping.generated_values;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.Session;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.tuple.ValueGenerator;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestGeneratorTypeAnnotation extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			CurrentUser.INSTANCE.logIn("Alice");

			Person4 person4 = new Person4();
			person4.setFirstName("John");
			person4.setLastName("Doe");

			entityManager.persist(person4);

			CurrentUser.INSTANCE.logOut();

			CurrentUser.INSTANCE.logIn("Bob");

			person4 = entityManager.find(Person4.class, 1L);
			person4.setFirstName("Mr. John");
		});

		entityManagerFactory.close();

		CurrentUser.INSTANCE.logOut();

	}

}

class CurrentUser {

	public static final CurrentUser INSTANCE = new CurrentUser();

	private static final ThreadLocal<String> storage = new ThreadLocal<>();

	public void logIn(String user) {
		storage.set(user);
	}

	public void logOut() {
		storage.remove();
	}

	public String get() {
		return storage.get();
	}
}

class LoggedUserGenerator implements ValueGenerator<String> {

	@Override
	public String generateValue(Session session, Object owner) {
		return CurrentUser.INSTANCE.get();
	}
}

@Entity(name = "Persons4")
class Person4 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@GeneratorType(type = LoggedUserGenerator.class, when = GenerationTime.INSERT)
	private String createdBy;
	@GeneratorType(type = LoggedUserGenerator.class, when = GenerationTime.ALWAYS)
	private String updatedBy;

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

	public String getCreatedBy() {
		return createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

}