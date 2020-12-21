package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.ordered_lists;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestUnidirectionalOrderBy extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Person person0 = entityManager.find(Person.class, 1L);
			if (person0 != null)
				entityManager.remove(person0);
			entityManager.flush();
			entityManager.clear();

			Person person = new Person();
			person.setId(1L);
			List<Phone> phones = new ArrayList<>();
			phones.add(new Phone(1L, "landline", "628-536-1074"));
			phones.add(new Phone(2L, "mobile", "828-634-4872"));
			phones.add(new Phone(3L, "landline", "228-332-0732"));
			phones.add(new Phone(4L, "mobile", "128-134-3678"));
			person.setPhones(phones);

			entityManager.persist(person);

			entityManager.flush();
			entityManager.clear();

			Person person2 = entityManager.find(Person.class, 1L);
			person2.getPhones().forEach(System.out::println);

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Person18")
@Table(name = "Persons18")
class Person {

	@Id
	private Long id;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	// The child table column is used to order the list elements.
	// The @OrderBy annotation can take multiple entity properties, and each
	// property
	// can take an ordering direction too (e.g. @OrderBy("name ASC, type DESC")).
	// If no property is specified (e.g. @OrderBy), the primary key of the child
	// entity
	// table is used for ordering.
	@OrderBy("number")
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

@Entity(name = "Phone12")
@Table(name = "Phones12")
class Phone {

	@Id
	private Long id;
	private String type;
	@Column(name = "`number`")
	private String number;

	public Phone() {
	}

	public Phone(long id, String type, String number) {
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.type + ": " + this.number;
	}

}

/*
 * SELECT phones0_.Person_id AS Person_i1_1_0_, phones0_.phones_id AS
 * phones_i2_1_0_, unidirecti1_.id AS id1_2_1_, unidirecti1_."number" AS
 * number2_2_1_, unidirecti1_.type AS type3_2_1_ FROM Person_Phone phones0_
 * INNER JOIN Phone unidirecti1_ ON phones0_.phones_id=unidirecti1_.id WHERE
 * phones0_.Person_id = 1 ORDER BY unidirecti1_."number"
 */