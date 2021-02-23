package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.HashMap;
import java.util.Map;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestLiterals extends BaseTest {

	public static void main(String[] args) {
		
		Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.format_sql", "false");
		rebuildEntityManagerFactory(properties);
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			Person person = new Person();
			person.setId(100L);
			person.setName("Joe's");
			person.setNickName("joe");
			
			entityManager.persist(person);
			entityManager.flush();
			entityManager.clear();
			
			// String literals.
			System.out.println(
				entityManager.createQuery(
					"select p " +
					"from Person44 p " +
					"where p.name like 'name1'", Person.class)
				.getSingleResult()
			);
			// Escaping quotes.
			System.out.println(
				entityManager.createQuery(
					"select p " +
					"from Person44 p " +
					"where p.name like 'Joe''s'", Person.class)
				.getSingleResult()
			);
			
			entityManager.remove(entityManager.merge(person));


		});

		entityManagerFactory.close();
	}
}

/*

// simple integer literal
Person person = entityManager.createQuery(
	"select p " +
	"from Person p " +
	"where p.id = 1", Person.class)
.getSingleResult();

// simple integer literal, typed as a long
Person person = entityManager.createQuery(
	"select p " +
	"from Person p " +
	"where p.id = 1L", Person.class)
.getSingleResult();

// decimal notation
List<Call> calls = entityManager.createQuery(
	"select c " +
	"from Call c " +
	"where c.duration > 100.5", Call.class )
.getResultList();

// decimal notation, typed as a float
List<Call> calls = entityManager.createQuery(
	"select c " +
	"from Call c " +
	"where c.duration > 100.5F", Call.class )
.getResultList();

// scientific notation
List<Call> calls = entityManager.createQuery(
	"select c " +
	"from Call c " +
	"where c.duration > 1e+2", Call.class )
.getResultList();

// scientific notation, typed as a float
List<Call> calls = entityManager.createQuery(
	"select c " +
	"from Call c " +
	"where c.duration > 1e+2F", Call.class )
.getResultList();

 */
