package by.pva.hibernate.part01.caching;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.CacheStoreMode;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.CacheMode;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.jpa.QueryHints;

import by.pva.hibernate.part01._myUtils.BaseTest;

@SuppressWarnings("unused")
public class TestCacheMode extends BaseTest {

	public static void main(String[] args) {

		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.cache.use_second_level_cache", "true");
		properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		properties.put("hibernate.cache.use_query_cache", "true");

		rebuildEntityManagerFactory(properties);

		doInJPA(entityManager -> {

			Query query1 = entityManager.createQuery("Delete from Person43");
			query1.executeUpdate();

			Person43 person1 = new Person43();
			person1.setId(1L);
			person1.setName("Vasia");
			Person43 person2 = new Person43();
			person2.setId(2L);
			person2.setName("Petia");
			Person43 person3 = new Person43();
			person3.setId(3L);
			person3.setName("Grisha");

			entityManager.persist(person1);
			entityManager.persist(person2);
			entityManager.persist(person3);

			System.out.println("Session #1 is closed!");

		});

		doInJPA(entityManager -> {

			Map<String, Object> hints = new HashMap<>();
			hints.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE);
			hints.put("javax.persistence.cache.storeMode", CacheStoreMode.USE);
			Person43 person = entityManager.find(Person43.class, 1L, hints);

			// Using custom cache modes with Hibernate native API:
			// session.setCacheMode( CacheMode.NORMAL);
			// Person person = session.get(Person43.class, 1L);
			
			System.out.println("Session #2 is closed!");
		});

		doInJPA(entityManager -> {
			
			Map<String, Object> hints = new HashMap<>();
			hints.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE);
			hints.put("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
			Person43 person = entityManager.find(Person43.class, 1L, hints);
			
			System.out.println("Session #3 is closed!");
		});
		
		doInJPA(entityManager -> {
			
			List<Person43> persons = entityManager.createQuery("select p from Person43 p", Person43.class)
				                                  .setHint(QueryHints.HINT_CACHEABLE, "true")
				                                  .setHint("javax.persistence.cache.retrieveMode" , CacheRetrieveMode.USE)
				                                  .setHint("javax.persistence.cache.storeMode" , CacheStoreMode.USE)
				                                  .getResultList();
			
			// Using custom cache modes for queries with Hibernate native API
			// List<Person43> persons2 = session.createQuery("select p from Person43 p" )
			//					                 .setCacheable( true )
			//					                 .setCacheMode( CacheMode.REFRESH )
			//					                 .list();


			
			System.out.println("Session #4 is closed!");
		});

		doInJPA(entityManager -> {
			
			List<Person43> persons = entityManager.createQuery("select p from Person43 p", Person43.class)
				                                  .setHint(QueryHints.HINT_CACHEABLE, "true")
				                                  .setHint("javax.persistence.cache.retrieveMode" , CacheRetrieveMode.USE)
				                                  .setHint("javax.persistence.cache.storeMode" , CacheStoreMode.BYPASS)
				                                  .getResultList();			
			
			System.out.println("Session #5 is closed!");
		});

		entityManagerFactory.close();
	}
}

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "Person43")
@Table(name = "Persons43")
class Person43 {

	@Id
	private Long id;
	private String name;

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
}
