package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.ordered_lists;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Persistence;
import javax.persistence.Table;

public class TestUnidirectionalOrderColumn {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("by.pva.hibernate.part01.basicWithTableAutoGeneration");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Person19 person0 = entityManager.find(Person19.class, 1L);
		if(person0 != null)
			entityManager.remove(person0);
		entityManager.flush();
		entityManager.clear();
		
		Person19 person = new Person19();
		person.setId(1L);
		List<Phone13> phones = new ArrayList<>();
		phones.add(new Phone13(1L, "landline", "628-536-1074"));
		phones.add(new Phone13(2L, "mobile"  , "828-634-4872"));
		phones.add(new Phone13(3L, "landline", "228-332-0732"));
		phones.add(new Phone13(4L, "mobile"  , "128-134-3678"));
		person.setPhones(phones);

		entityManager.persist(person);
		
		entityManager.flush();
		entityManager.clear();

		Person19 person2 = entityManager.find(Person19.class, 1L);
		person2.getPhones().forEach(System.out::println);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

	}
}

@Entity(name = "Person19")
@Table(name = "Persons19")
class Person19 {

	@Id
	private Long id;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderColumn(name = "order_id") 
	private List<Phone13> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Phone13> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone13> phones) {
		this.phones = phones;
	}
}

@Entity(name = "Phone13")
@Table(name = "Phones13")
class Phone13 {

	@Id
	private Long id;
	private String type;
	@Column(name = "`number`")
	private String number;

	public Phone13() {
	}
	
	public Phone13(long id, String type, String number) {
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
select
   phones0_.Person_id as Person_i1_1_0_,
   phones0_.phones_id as phones_i2_1_0_,
   phones0_.order_id as order_id3_0_,
   unidirecti1_.id as id1_2_1_,
   unidirecti1_.number as number2_2_1_,
   unidirecti1_.type as type3_2_1_
from
   Person_Phone phones0_
inner join
   Phone unidirecti1_
      on phones0_.phones_id=unidirecti1_.id
where
   phones0_.Person_id = 1
 */