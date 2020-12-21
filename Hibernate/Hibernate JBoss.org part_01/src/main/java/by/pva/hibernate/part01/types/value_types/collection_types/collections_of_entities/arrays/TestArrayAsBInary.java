package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestArrayAsBInary extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from Person32");
			query.executeUpdate();

			Person person = new Person();
			person.setId(1L);
			String[] phones = { "+375(29)768-15-34", "+375(29)400-37-91", "+375(29)999-62-55" };
			person.setPhones(phones);
			entityManager.persist(person);

			entityManager.flush();
			entityManager.clear();

			String[] phones2 = entityManager.find(Person.class, 1L).getPhones();
			for (String phone : phones2) {
				System.out.println(phone);
			}

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Person32")
@Table(name = "Persons32")
class Person {

	@Id
	private Long id;
	private String[] phones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String[] getPhones() {
		return phones;
	}

	public void setPhones(String[] phones) {
		this.phones = phones;
	}
}

// CREATE TABLE Person (
//    id BIGINT NOT NULL ,
//    phones VARBINARY(255) ,
//    PRIMARY KEY ( id )
// )
