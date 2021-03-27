package by.pva.hibernate.part01.native_query;

import java.util.List;

import org.hibernate.Session;

import _by.pva.hibernate.part01.hql_jpql.domain_model.Person;
import by.pva.hibernate.part01._myUtils.BaseTest;
import by.pva.hibernate.part01._myUtils.HqlJpqlDBUtils;

public class TestQueryParameters extends BaseTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		HqlJpqlDBUtils.prepareDomainModel();

		doInJPA(entityManager -> {
			
			// JPA native query with parameters
			List<Person> persons1 = entityManager.createNativeQuery(
				"SELECT * " +
				"FROM Persons44 " +
				"WHERE name like :name", Person.class)
			.setParameter("name", "name1")
			.getResultList();

			// Hibernate native query with parameters
			List<Person> persons2 = entityManager.unwrap(Session.class).createNativeQuery(
				"SELECT * " +
				"FROM Persons44 " +
				"WHERE name like :name")
			.addEntity(Person.class)
			.setParameter("name", "name2")
			.list();

			persons1.forEach(System.out::println);
			persons2.forEach(System.out::println);
			
		});

		entityManagerFactory.close();
	}
	
}