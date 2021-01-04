package by.pva.hibernate.part01.caching;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Cacheable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestEntityCache extends BaseTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.cache.use_second_level_cache", "true");
		properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");

		rebuildEntityManagerFactory(properties);

		doInJPA(entityManager -> {

			Query query1 = entityManager.createQuery("Delete from Phone25");
			query1.executeUpdate();

			Phone25 phone25_1 = new Phone25();
			phone25_1.setId(1L);
			phone25_1.setNumber("+37529 567-98-11");

			entityManager.persist(phone25_1);

			entityManager.flush();
			entityManager.clear();

			Phone25 phone25_2 = entityManager.find(Phone25.class, 1L);
			entityManager.flush();
			entityManager.clear();
			
			Phone25 phone25_3 = entityManager.find(Phone25.class, 1L);

			System.out.println("End session #1");

		});

		doInJPA(entityManager -> {
			Phone25 phone25_4 = entityManager.find(Phone25.class, 1L);
			System.out.println("End session #2");
		});
		
		doInJPA(entityManager -> {
			Phone25 phone25_5 = entityManager.find(Phone25.class, 1L);
			System.out.println("End session #3");
		});
		
		doInJPA(entityManager -> {
			Phone25 phone25_6 = entityManager.find(Phone25.class, 1L);
			System.out.println("End session #4");
		});
		
		doInJPA(entityManager -> {
			Phone25 phone25_7 = entityManager.find(Phone25.class, 1L);
			System.out.println("End session #5");
		});
		
		entityManagerFactory.close();
	}
}

// https://stackoverflow.com/questions/37697028/hibernate-ehcache-second-level-cache-miss-in-simple-example
// But, before returning the entity, it is stored in first level cache also so that next invocation to load method for entity will return the entity
// from first level cache itself, and there will not be need to go to second level cache again.
// https://www.java67.com/2017/10/difference-between-first-level-and-second-level-cache-in-Hibernate.html

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE) // change!
@Entity(name = "Phone25")
@Table(name = "Phones25")
class Phone25 {

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