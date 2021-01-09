package by.pva.hibernate.part01.caching;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import by.pva.hibernate.part01._myUtils.BaseTest;

public class TestQueryCache extends BaseTest {

	public static void main(String[] args) {

		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.cache.use_second_level_cache", "true");
		properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		properties.put("hibernate.cache.use_query_cache", "true"); // !!! Enable query cache!

		rebuildEntityManagerFactory(properties);

		doInJPA(entityManager -> {

			Query query1 = entityManager.createQuery("Delete from Person42");
			query1.executeUpdate();

			Person42 person1 = new Person42();
			person1.setId(1L);
			person1.setName("Vasia");
			Person42 person2 = new Person42();
			person2.setId(2L);
			person2.setName("Petia");
			Person42 person3 = new Person42();
			person3.setId(3L);
			person3.setName("Grisha");

			entityManager.persist(person1);
			entityManager.persist(person2);
			entityManager.persist(person3);

			System.out.println("Session #1 is closed!");

		});

		doInJPA(entityManager -> {

			List<Person42> persons = entityManager
					.createQuery("select p " + "from Person42 p", Person42.class)
					.setHint("org.hibernate.cacheable", "true")
					.getResultList();
			persons.size();

			System.out.println("Session #2 is closed!");
		});

		doInJPA(entityManager -> {
			List<Person42> persons = entityManager
					.createQuery("select p " + "from Person42 p", Person42.class)
					.setHint("org.hibernate.cacheable", "true")
					.getResultList();
			persons.size();
			System.out.println("Session #3 is closed!");
		});

		entityManagerFactory.close();
	}
}

/*
For entity queries, the query cache does not cache the state of the actual entities. Instead, it stores the entity identifiers,
and when the query result is fetched from the cache, the entity state is going to be loaded from the second-level cache entity regions.
Just as with collection caching, the query cache should always be used in conjunction with the second-level cache for those entities 
expected to be cached as part of a query result cache.
For projection queries, the query cache stores the dehydrated entity state (e.g. Object[]) associated with the underlying JDBC ResultSet.
*/

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "Person42")
@Table(name = "Persons42")
class Person42 {

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
