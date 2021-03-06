package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_value_types;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestCollectionsOfEmbeddables extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person15 person = new Person15();

			person.getPhones().add(new Phone("landline", "028-234-9876"));
			person.getPhones().add(new Phone("mobile", "072-122-9876"));

			entityManager.persist(person);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Person15")
@Table(name = "persons15")
class Person15 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ElementCollection
	private List<Phone> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
}

@Embeddable
class Phone {

	private String type;
	@Column(name = "`number`")
	private String number;

	public Phone(String type, String number) {
		this.type = type;
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
