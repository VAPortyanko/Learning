package by.pva.hibernate.part01.types.value_types.collection_types.collection_as_basic_value_type;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestCollectionAsBasicVakueType extends BaseTest {

	@SuppressWarnings("serial")
	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person person = new Person();
			List<String> phones = new ArrayList<String>() {
				{
					add("+375(29) 980-12-01");
					add("+375(29) 980-12-02");
					add("+375(29) 980-12-03");
				}
			};
			person.setPhones(phones);

			entityManager.persist(person);

		});

		entityManagerFactory.close();
	}
}

@Entity(name = "Person33")
@Table(name = "Persons33")
class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// ToDo - Example doesn't work with the line below - why? (The new type must be
	// registered?)
	// @Type(type = "comma_delimited_strings")
	@Type(type = "by.pva.hibernate.part01.types.value_types.collection_types.collection_as_basic_value_type.CommaDelimitedStringsType")

	private List<String> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public List<String> getPhones() {
		return phones;
	}
}