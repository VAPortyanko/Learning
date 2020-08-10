package by.pva.hibernate.part01.types.attribute_conventer;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

public class TestAttributeConventer {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		Person person = new Person();
		person.setGender(Gender.MALE);
		person.setName("Vasia");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(person);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}

enum Gender {
	MALE('M'), FEMALE('F');

	private final char code;

	Gender(char code) {
		this.code = code;
	}

	public static Gender fromCode(char code) {
		if (code == 'M' || code == 'm') {
			return MALE;
		}
		if (code == 'F' || code == 'f') {
			return FEMALE;
		}
		throw new UnsupportedOperationException("The code " + code + " is not supported!");
	}

	public char getCode() {
		return code;
	}
}

@Entity(name = "Persons")
class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Convert(converter = GenderConverter.class)
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

@Converter
class GenderConverter implements AttributeConverter<Gender, Character> {

	public Character convertToDatabaseColumn(Gender value) {
		if (value == null) {
			return null;
		}
		return value.getCode();
	}

	public Gender convertToEntityAttribute(Character value) {
		if (value == null) {
			return null;
		}
		return Gender.fromCode(value);
	}
}
