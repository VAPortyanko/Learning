package by.pva.hibernate.part01.hql_jpql.query_api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import _by.pva.hibernate.part01.hql_jpql.domain_model.Call;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01.hql_jpql.domain_model.utils.HqlJpqlDBUtils;

public class TestExpressions_Literals extends BaseTest {

	@SuppressWarnings("unused")
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
			// Escaping quotes (String literals are enclosed in single quotes. 
			// To escape a single quote within a string literal, use double single quotes.).
			System.out.println(
				entityManager.createQuery(
					"select p " +
					"from Person44 p " +
					"where p.name like 'Joe''s'", Person.class)
				.getSingleResult()
			);
			
			// Delete detached entity.
			entityManager.remove(entityManager.merge(person));
			
			// simple integer literal.
			Person person1 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.id = 1", Person.class)
			.getSingleResult();
			
			// simple integer literal, typed as a long.
			Person person2 = entityManager.createQuery(
				"select p " +
				"from Person44 p " +
				"where p.id = 1L", Person.class)
			.getSingleResult();
			
			// decimal notation.
			List<Call> calls2 = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.duration > 10.5", Call.class )
			.getResultList();
			
			// decimal notation, typed as a float
			List<Call> calls3 = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.duration > 10.5F", Call.class )
			.getResultList();

			// scientific notation
			List<Call> calls4 = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.duration > 1e+1", Call.class )
			.getResultList();

			// scientific notation, typed as a float.
			List<Call> calls5 = entityManager.createQuery(
				"select c " +
				"from Call c " +
				"where c.duration > 1e+2F", Call.class )
			.getResultList();

		});

		entityManagerFactory.close();
	}
}

/*
Date/time literals can be specified using the JDBC escape syntax:
    for dates      - {d 'yyyy-mm-dd'}
    for times      - {t 'hh:mm:ss'} 
    for timestamps - {ts 'yyyy-mm-dd hh:mm:ss[.millis]'} (millis optional) 
These Date/time literals only work if the underlying JDBC driver supports them.

The boolean literals are TRUE and FALSE, case-insensitive.

Enums can even be referenced as literals. 
The fully-qualified enum class name must be used. 
HQL can also handle constants in the same manner, though JPQL does not define that as being supported.

Entity names can also be used as literal.




 */
