package by.pva.hibernate.part01.types.value_types.collection_types.custom_collection_type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionType;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestCustomCollectionType extends BaseTest {

	public static void main(String[] args) {

		doInJPA(entityManager -> {

			Query query = entityManager.createQuery("delete from Person34");
			Query query2 = entityManager.createQuery("delete from Phone22");
			query.executeUpdate();
			query2.executeUpdate();

			Person34 person = new Person34();
			person.setId(1L);
			Collection<Phone22> phones = new ArrayList<>();
			phones.add(new Phone22("mobile", "+375(29) 968-06-05"));
			phones.add(new Phone22("mobile", "+375(29) 968-06-06"));
			phones.add(new Phone22("mobile", "+375(29) 968-06-07"));
			person.setPhones(phones);
			entityManager.persist(person);
			entityManager.flush();
			entityManager.clear();

			Person34 person2 = entityManager.find(Person34.class, 1L);
			Queue<Phone22> phones2 = person2.getPhones();
			System.out.println(phones2.peek());

		});

		entityManagerFactory.close();

	}
}

@Entity(name = "Person34")
@Table(name = "Persons34")
class Person34 {

	@Id
	private Long id;
	@OneToMany(cascade = CascadeType.ALL)
	@CollectionType(type = "by.pva.hibernate.part01.types.value_types.collection_types.QueueType")
	private Collection<Phone22> phones = new LinkedList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPhones(Collection<Phone22> phones) {
		this.phones = phones;
	}

	public Queue<Phone22> getPhones() {
		return (Queue<Phone22>) phones;
	}
}

@Entity(name = "Phone22")
@Table(name = "Phones22")
class Phone22 implements Comparable<Phone22> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	@Column(name = "`number`")
	private String number;

	public Phone22() {
	}

	public Phone22(String type, String number) {
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

	@Override
	public int compareTo(Phone22 o) {
		return number.compareTo(o.getNumber());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Phone22 phone = (Phone22) o;
		return Objects.equals(number, phone.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public String toString() {
		return type + ": " + number;
	}
}