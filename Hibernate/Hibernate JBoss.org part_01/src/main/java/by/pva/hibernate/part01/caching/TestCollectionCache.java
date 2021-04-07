package by.pva.hibernate.part01.caching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestCollectionCache extends BaseTest{

	public static void main(String[] args) {
		
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.cache.use_second_level_cache", "true");
		properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");

		buildEntityManagerFactory(properties);

		doInJPA(entityManager -> {

			Query query1 = entityManager.createNativeQuery("Delete from Person41_hobbies");
			Query query2 = entityManager.createQuery("Delete from Person41");
			Query query3 = entityManager.createQuery("Delete from Phone26");
			query1.executeUpdate();
			query2.executeUpdate();
			query3.executeUpdate();
			
			Phone26 phone26_1 = new Phone26();
			phone26_1.setId(1L);
			phone26_1.setNumber("+37529 567-98-11");
			
			Phone26 phone26_2 = new Phone26();
			phone26_2.setId(2L);
			phone26_2.setNumber("+37529 567-98-12");
			
			Phone26 phone26_3 = new Phone26();
			phone26_3.setId(3L);
			phone26_3.setNumber("+37529 567-98-13");
			
			List<Phone26> phones = new ArrayList<>();
			phones.add(phone26_1);
			phones.add(phone26_2);
			phones.add(phone26_3);
			List<String> hobbies = new ArrayList<>();
			hobbies.add("Music");
			hobbies.add("Dance");
			hobbies.add("Sport");
			
			Person41 person = new Person41();
			person.setId(1L);
			person.setName("Vasia");
			person.setPhones(phones);
			person.setHobbies(hobbies);
			
			entityManager.persist(person);
			
			entityManager.flush();
			
			System.out.println("Session #1 is closed!");
			
		});
		
		doInJPA(entityManager -> {
			Person41 person = entityManager.find(Person41.class, 1L);
			person.getPhones().size();
			person.getHobbies().size();
			System.out.println("Session #2 is closed!");
		});
		
		doInJPA(entityManager -> {
			Person41 person = entityManager.find(Person41.class, 1L);
			person.getPhones().size();
			person.getHobbies().size();
			System.out.println("Session #3 is closed!");
		});
		
		entityManagerFactory.close();
	}
}

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "Person41")
@Table(name = "Persons41")
class Person41 {
	@Id
	private Long id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Phone26> phones = new ArrayList<>();
	@ElementCollection
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<String> hobbies = new ArrayList<>();
	
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
	public List<Phone26> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone26> phones) {
		this.phones = phones;
	}
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

}

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "Phone26")
@Table(name = "Phones26")
class Phone26 {

	@Id
	private Long id;
	private String number;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
// Read later - https://vladmihalcea.com/how-does-hibernate-collection-cache-work/